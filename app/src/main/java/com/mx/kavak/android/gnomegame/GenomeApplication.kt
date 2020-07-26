package com.mx.kavak.android.gnomegame

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GenomeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initFirebaseToken()
    }

    private fun initFirebaseToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task: Task<InstanceIdResult?> ->
                if (!task.isSuccessful)
                    return@addOnCompleteListener
                if (task.result != null) {
                    Log.v("FB TOKEN", task.result!!.token)
                }
            }
    }

}