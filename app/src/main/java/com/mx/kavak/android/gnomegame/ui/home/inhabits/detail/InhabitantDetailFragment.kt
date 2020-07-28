package com.mx.kavak.android.gnomegame.ui.home.inhabits.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.databinding.FragmentInhabitantDetailBinding
import com.mx.kavak.android.gnomegame.ui.home.inhabits.adapter.FriendsAdapter
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.mx.kavak.android.gnomegame.ui.home.inhabits.detail.InhabitantDetailViewModel.UiModel
import com.mx.kavak.android.gnomegame.ui.home.inhabits.dialogs.ProfessionsDialog

class InhabitantDetailFragment : Fragment() {

    private lateinit var binding: FragmentInhabitantDetailBinding
    private val args: InhabitantDetailFragmentArgs by navArgs()
    private lateinit var inhabitant: Inhabitant
    private val viewModel: InhabitantDetailViewModel by currentScope.viewModel(this) {
        parametersOf(inhabitant)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inhabitant = args.inhabitant
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInhabitantDetailBinding.inflate(layoutInflater)
        val view: View = binding.root

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = " "
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        binding.fbFavorite.setOnClickListener {
            if (inhabitant.isFavorite) {
                binding.fbFavorite.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.gray),
                    android.graphics.PorterDuff.Mode.SRC_IN)
                viewModel.changeFavoriteStatus(inhabitant.id, false)
            } else {
                binding.fbFavorite.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                    android.graphics.PorterDuff.Mode.SRC_IN)
                viewModel.changeFavoriteStatus(inhabitant.id, true)
            }
        }
        
        binding.btnShowProfessions.setOnClickListener {
            val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
            val height = (resources.displayMetrics.heightPixels * 0.50).toInt()

            val dialog = ProfessionsDialog(requireActivity(), inhabitant.professions)
            dialog.window?.setLayout(width, height)
            dialog.setCancelable(false)
            dialog.show()
        }

        viewModel.model.observe(requireActivity(), Observer(::updateUI))
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_inhabitantDetailFragment_to_inhabitsFragment)
        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("SetTextI18n")
    private fun updateUI(model: UiModel) =
        when (model) {
            is UiModel.DisplayInhabitantInfo -> {
                with(model.inhabitant) {
                    binding.ivGnome.loadImage(photo)
                    binding.tvGnomeName.text = name
                    binding.tvGnomeAge.text = "(${age} years)"
                    binding.tvGnomeHairColor.text = "Hair color: $hair_color"
                    binding.tvGnomeWeight.text = "Weight: $weight"
                    binding.tvGnomeHeight.text = "Height: $height"
                    binding.fbFavorite.setColorFilter(
                        if (isFavorite)
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                        else ContextCompat.getColor(requireContext(), R.color.gray),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    if (friends.isEmpty()) {
                        binding.tvGnomeFriendsTitle.chageVisibility(View.GONE)
                        binding.viewFriends.chageVisibility(View.GONE)
                    }
                }

                val adapter = FriendsAdapter(childFragmentManager, viewModel.readFriends())
                binding.viewPagerTypeServices.adapter = adapter
                binding.vpIndicator.setViewPager(binding.viewPagerTypeServices)

            }
        }

}