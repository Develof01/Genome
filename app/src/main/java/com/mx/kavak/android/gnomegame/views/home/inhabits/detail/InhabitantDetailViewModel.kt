package com.mx.kavak.android.gnomegame.views.home.inhabits.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.utils.ScopedViewModel
import com.mx.kavak.android.usecases.InhabitantsUseCase
import kotlinx.coroutines.*

class InhabitantDetailViewModel(
    private val inhabitant: Inhabitant,
    private val inhabitantsUseCase: InhabitantsUseCase
) : ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel.DisplayInhabitantInfo(inhabitant)
            return _model
        }

    fun readFriends(): List<Inhabitant> {
        var friends = emptyList<Inhabitant>()
        runBlocking {
            val job = GlobalScope.launch(Dispatchers.IO) {
                friends = inhabitantsUseCase.readInhabitantFriends(inhabitant.id)
            }
            job.join()
        }
        return friends
    }

    fun changeFavoriteStatus(id: Int, isFavorite: Boolean) {
        launch {
            withContext(Dispatchers.IO) {
                inhabitantsUseCase.changeFavoriteInhabitantStatus(id, isFavorite)
            }
        }
    }

    sealed class UiModel {
        class DisplayInhabitantInfo(val inhabitant: Inhabitant) : UiModel()
    }

}