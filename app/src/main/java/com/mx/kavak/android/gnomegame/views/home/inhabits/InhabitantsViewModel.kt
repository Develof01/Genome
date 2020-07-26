package com.mx.kavak.android.gnomegame.views.home.inhabits


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mx.kavak.android.domain.dto.response.InhabitantsResponse
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.domain.models.ResultWrapper
import com.mx.kavak.android.gnomegame.data.*
import com.mx.kavak.android.gnomegame.data.database.AppDatabase
import com.mx.kavak.android.gnomegame.data.database.dao.InhabitantDao
import com.mx.kavak.android.gnomegame.data.database.dao.InhabitantFriendsDao
import com.mx.kavak.android.gnomegame.data.database.dao.InhabitantProfessionDao
import com.mx.kavak.android.gnomegame.utils.ScopedViewModel
import com.mx.kavak.android.usecases.InhabitantsUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class InhabitantsViewModel @ViewModelInject constructor(
    db: AppDatabase,
    private val inhabitantsUseCase: InhabitantsUseCase
) : ScopedViewModel() {
    private var currentName = ""
    private var inhabitantName: MutableLiveData<String>? = null
    private var isRefreshing = false

    private var inhabitantDao: InhabitantDao? = null
    private var inhabitantFriendsDao: InhabitantFriendsDao? = null
    private var inhabitantProfessionDao: InhabitantProfessionDao? = null

    private var _inhabitants: LiveData<List<com.mx.kavak.android.gnomegame.data.database.entities.Inhabitant>>? =
        null
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }
    val localInhabitants: LiveData<List<com.mx.kavak.android.gnomegame.data.database.entities.Inhabitant>>?
        get() {
            return _inhabitants
        }


    init {
        inhabitantDao = db.inhabitantDao()
        inhabitantFriendsDao = db.inhabitantFriendsDao()
        inhabitantProfessionDao = db.inhabitantProfessionDao()
        inhabitantName = MutableLiveData()
        inhabitantName!!.value = ""
        initScope()
        getAllLocalInhabitants()
        readAllInhabits()
    }

    private fun getAllLocalInhabitants() {
        _inhabitants = Transformations.switchMap(inhabitantName!!) {
            temp(inhabitantName!!.value!!)
        }
    }

    fun searchAllLocalInhabitantsByName(name: String) {
        currentName = name
        inhabitantName!!.value = name
    }


    private fun temp(name: String): LiveData<List<com.mx.kavak.android.gnomegame.data.database.entities.Inhabitant>>? {
        return inhabitantDao!!.findAllByName(name)
    }

    private fun getFriends(idInhabitant: Int): List<String> {
        var friends = emptyList<String>()
        runBlocking {
            val job = GlobalScope.launch(IO) {
                friends = inhabitantFriendsDao!!.findByInhabitantId(idInhabitant).map {
                    it!!.toDomainInhabitantFriends()
                }
            }
            job.join()
        }
        return friends
    }


    private fun getProfessions(idInhabitant: Int): List<String> {
        var professions = emptyList<String>()
        runBlocking {
            val job = GlobalScope.launch(IO) {
                professions = inhabitantProfessionDao!!.findByInhabitantId(idInhabitant).map {
                    it!!.toDomainInahabitantProgessions()
                }
            }
            job.join()
        }
        return professions
    }

    fun readAllInhabits() {
        if (!isRefreshing) {
            isRefreshing = true
            launch {
                validateInhabitsResponse(inhabitantsUseCase.readInhabits())
            }
        }
    }

    private fun validateInhabitsResponse(response: ResultWrapper<InhabitantsResponse?>) {
        isRefreshing = false
        when (response) {
            is ResultWrapper.Success -> {
                launch {
                    withContext(IO) {
                        response.value?.let { inhabitants ->
                            inhabitantDao!!.insertInhabitants(inhabitants.Brastlewark.map {
                                it.toRoomInhabitant()
                            })
                            for (inhabitant in inhabitants.Brastlewark) {
                                val friends = inhabitantFriendsDao!!.findByInhabitantId(inhabitant.id)
                                val professions = inhabitantProfessionDao!!.findByInhabitantId(inhabitant.id)
                                if (friends.isEmpty() && inhabitant.friends.isNotEmpty())
                                inhabitantFriendsDao!!.insertInhabitantFriends(inhabitant.friends.map {
                                    it.toRoomFriends(inhabitant.id)
                                })
                                if (professions.isEmpty() && inhabitant.professions.isNotEmpty())
                                inhabitantProfessionDao!!.insertInhabitantProfessions(inhabitant.professions.map {
                                    it.toRoomProfessions(inhabitant.id)
                                })
                            }
                        }
                    }
                }
            }
            is ResultWrapper.GenericError -> {
                _model.value = UiModel.DisplayError(response.errorDesc!!)
            }
            is ResultWrapper.NetworkError -> {
                _model.value = UiModel.DisplayNetworkError(response.networkError)
            }
        }
        _model.value = UiModel.DismissLoading
    }

    fun onMovieClicked(inhabitant: Inhabitant) {

        inhabitant.friends = getFriends(inhabitant.id)
        inhabitant.professions = getProfessions(inhabitant.id)

        _model.value = UiModel.NavigateToDetail(inhabitant)
    }

    fun changeFavoriteStatus(id: Int, isFavorite: Boolean) {
        launch {
            withContext(IO) {
                inhabitantDao!!.updateFavoriteStatus(id, isFavorite)
            }
            inhabitantName!!.value = currentName
        }
    }

    sealed class UiModel {
        object DismissLoading : UiModel()
        class DisplayError(val message: String) : UiModel()
        class DisplayNetworkError(val message: String): UiModel()
        class NavigateToDetail(val inhabitant: Inhabitant) : UiModel()
    }
}