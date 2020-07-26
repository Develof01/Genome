package com.mx.kavak.android.gnomegame.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navBottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_global_inhabitsFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_notifications -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_global_notificationFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_global_profileFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}