package com.mx.kavak.android.gnomegame.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mx.kavak.android.domain.models.ProfileData
import com.mx.kavak.android.gnomegame.databinding.FragmentProfileBinding
import com.mx.kavak.android.gnomegame.utils.SimpleDividerItemDecoration
import com.mx.kavak.android.gnomegame.ui.home.profile.adapter.ProfileDataAdapter


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: ProfileDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        val view: View = binding.root
        initComponents()
        return view
    }

    private fun initComponents() {
        adapter = ProfileDataAdapter(requireContext())
        val data = mutableListOf<ProfileData>()
        data.add(ProfileData(0, "Celular", "55-8200 8928"))
        data.add(ProfileData(1, "LinkedIn", "https://www.linkedin.com/in/ofloresb/"))
        data.add(ProfileData(2, "GitHub", "https://github.com/Develof01"))

        adapter.documentation = data
        binding.rvData.addItemDecoration(SimpleDividerItemDecoration(requireContext()))
        binding.rvData.adapter = adapter
    }

}