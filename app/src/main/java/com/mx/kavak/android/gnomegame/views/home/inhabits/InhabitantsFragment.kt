package com.mx.kavak.android.gnomegame.views.home.inhabits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.data.toDomainInhabitant
import com.mx.kavak.android.gnomegame.databinding.FragmentInhabitsBinding
import com.mx.kavak.android.gnomegame.extensions.chageVisibility
import com.mx.kavak.android.gnomegame.extensions.toast
import com.mx.kavak.android.gnomegame.utils.Constant
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mx.kavak.android.gnomegame.views.home.inhabits.InhabitantsViewModel.UiModel
import com.mx.kavak.android.gnomegame.views.home.inhabits.adapter.InhabitsAdapter

class InhabitantsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    FloatingSearchView.OnSearchListener {

    private lateinit var binding: FragmentInhabitsBinding
    private val viewModel: InhabitantsViewModel by currentScope.viewModel(this)
    private lateinit var adapter: InhabitsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInhabitsBinding.inflate(layoutInflater)
        val view: View = binding.root

        binding.swipeRefreshInhabitant.setOnRefreshListener(this)
        binding.swipeRefreshInhabitant.setColorSchemeColors(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )

        binding.svInhabitant.setOnSearchListener(this)

        binding.svInhabitant.setOnQueryChangeListener { _, newQuery ->
            viewModel.searchAllLocalInhabitantsByName(newQuery!!)
        }

        adapter = InhabitsAdapter(viewModel::onMovieClicked, viewModel::changeFavoriteStatus)

        viewModel.localInhabitants?.observe(requireActivity(), Observer { inhabitants ->
            if (inhabitants.isNotEmpty()) {
                dismissLoading()
                val domainInhabitant = inhabitants
                    .map {
                        it.toDomainInhabitant(emptyList(), emptyList())
                    }
                    .sortedBy { it.id }
                displayInhabitants(domainInhabitant)
            }
        })

        viewModel.model.observe(requireActivity(), Observer(::updateUi))
        return view
    }

    private fun updateUi(model: UiModel) {
        when (model) {

            is UiModel.DismissLoading -> {
                dismissLoading()
            }

            is UiModel.NavigateToDetail -> {
                val bundle = Bundle()
                bundle.putSerializable("inhabitant", model.inhabitant)
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_inhabitsFragment_to_inhabitantDetailFragment, bundle)
            }

            is UiModel.DisplayError -> {
                requireActivity().toast(model.message, Toast.LENGTH_LONG)
            }

            is UiModel.DisplayNetworkError -> {
                requireActivity().toast(model.message, Toast.LENGTH_LONG)
            }

        }
    }

    private fun displayInhabitants(inhabitants: List<Inhabitant>) {
            adapter.items = inhabitants
            if (binding.rvInhabits.adapter == null) {
                binding.rvInhabits.apply {
                    adapter = this@InhabitantsFragment.adapter
                    layoutManager = GridLayoutManager(context, Constant.COL_NUM)
                }
            } else {
                adapter.notifyDataSetChanged()
            }
    }

    private fun dismissLoading() {
        binding.loaderView.chageVisibility(View.GONE)
        if (binding.swipeRefreshInhabitant.isRefreshing) {
            binding.swipeRefreshInhabitant.isRefreshing = false
        }
    }

    override fun onRefresh() {
        viewModel.readAllInhabits()
    }

    override fun onSearchAction(currentQuery: String?) {
        viewModel.searchAllLocalInhabitantsByName(currentQuery!!)
    }

    override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
        TODO("Not yet implemented")
    }

}