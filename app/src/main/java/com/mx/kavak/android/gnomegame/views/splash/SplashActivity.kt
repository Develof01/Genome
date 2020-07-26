package com.mx.kavak.android.gnomegame.views.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.databinding.ActivitySplashBinding
import com.mx.kavak.android.gnomegame.extensions.chageVisibility
import com.mx.kavak.android.gnomegame.views.home.HomeActivity
import com.mx.kavak.android.gnomegame.views.splash.SplashViewModule.UiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModule by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.model.observe(this, Observer(::updateUi))

        binding.tvAppName.isAllCaps = true
        binding.btnStart.setOnClickListener {
            if (binding.tilUser.isVisible) {
                if (binding.etUser.text.toString().isNotEmpty()) {
                    viewModel.registerUser(binding.etUser.text.toString())
                } else {
                    binding.tilUser.error = resources.getString(R.string.error_enter_user)
                }
            } else {
                viewModel.initHomeActivity()
            }
        }
    }

    private fun updateUi(model: UiModel) {
        when (model) {

            is UiModel.AskForUser -> {
                binding.tilUser.chageVisibility(View.VISIBLE)
                binding.btnStart.text = resources.getString(R.string.accept)
            }

            is UiModel.DisplayWolcomeSign -> {
                val welcomeUser = resources.getString(R.string.welcome) + " ${model.username} "
                binding.btnStart.text = welcomeUser
            }

            is UiModel.NavigateToHome -> {
                val executeIntent = Intent(this@SplashActivity,
                    HomeActivity::class.java)
                startActivity(executeIntent)
                finish()
            }
        }
    }
}