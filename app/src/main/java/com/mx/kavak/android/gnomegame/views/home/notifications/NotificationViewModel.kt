package com.mx.kavak.android.gnomegame.views.home.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mx.kavak.android.domain.models.Notification
import com.mx.kavak.android.gnomegame.utils.ScopedViewModel
import com.mx.kavak.android.usecases.NotificationUseCase
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationUseCase: NotificationUseCase
): ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        initScope()
        displayNotifications()
    }

    fun displayNotifications() {
        launch {
            _model.value = UiModel.DisplayNotification(notificationUseCase.displayNotifications())
        }
    }


    sealed class UiModel {
        class DisplayNotification(val notifications: List<Notification>) : UiModel()
    }

}