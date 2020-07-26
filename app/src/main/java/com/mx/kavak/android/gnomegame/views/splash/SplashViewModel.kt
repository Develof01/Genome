package com.mx.kavak.android.gnomegame.views.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mx.kavak.android.domain.models.User
import com.mx.kavak.android.gnomegame.utils.ScopedViewModel
import com.mx.kavak.android.usecases.UserUseCase
import kotlinx.coroutines.launch

class SplashViewModel(private val userUseCase: UserUseCase) : ScopedViewModel() {


    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        initScope()
        checkLocalUser()
    }

    private fun checkLocalUser() {
        launch {
            userUseCase.getLocalUser()?.let {
                _model.value = UiModel.DisplayWolcomeSign(it.username)
            } ?: run {
                _model.value = UiModel.AskForUser
            }
        }
    }

    fun initHomeActivity() {
        _model.value = UiModel.NavigateToHome
    }

    fun registerUser(username: String) {
        launch {
            userUseCase.createUser(User(0, username, null))
            _model.value = UiModel.NavigateToHome
        }
    }

    sealed class UiModel {
        object AskForUser : UiModel()
        class DisplayWolcomeSign(val username: String): UiModel()
        object NavigateToHome : UiModel()
    }

}