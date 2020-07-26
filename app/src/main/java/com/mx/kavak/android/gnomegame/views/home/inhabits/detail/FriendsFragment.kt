package com.mx.kavak.android.gnomegame.views.home.inhabits.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.databinding.FragmentFriendsBinding
import com.mx.kavak.android.gnomegame.extensions.chageVisibility
import com.mx.kavak.android.gnomegame.extensions.loadImage


class FriendsFragment(
    private val firstInhabitant: Inhabitant?,
    private val secondInhabitant: Inhabitant?):
    Fragment() {

    private lateinit var binding: FragmentFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        val view: View = binding.root

        binding.ivFriendOne.loadImage(firstInhabitant!!.photo)
        binding.tvFriendNameOne.text = firstInhabitant.name

        if (secondInhabitant != null) {
            binding.ivFriendSecond.loadImage(secondInhabitant.photo)
            binding.tvFriendName.text = secondInhabitant.name
        } else {
            binding.cvSecondService.chageVisibility(View.GONE)
        }

        return view
    }
}