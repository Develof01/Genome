package com.mx.kavak.android.gnomegame.ui.home.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.databinding.FragmentNotificationBinding
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mx.kavak.android.gnomegame.ui.home.notifications.NotificationViewModel.UiModel
import com.mx.kavak.android.gnomegame.ui.home.notifications.adapter.NotificationAdapter


class NotificationFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentNotificationBinding
    private val viewModel: NotificationViewModel by currentScope.viewModel(this)
    private lateinit var adapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        val view: View = binding.root

        binding.swipeRefreshNotification.setOnRefreshListener(this)
        binding.swipeRefreshNotification.setColorSchemeColors(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )

        adapter = NotificationAdapter()
        viewModel.model.observe(requireActivity(), Observer(::updateUi))
        return view
    }


    private fun updateUi(model:UiModel) {
        when (model) {

            is UiModel.DisplayNotification -> {
                if (model.notifications.isNotEmpty()) {
                    adapter.notifications = model.notifications
                    binding.rvNotifications.adapter = adapter
                } else {
                    binding.content.content.visibility = View.VISIBLE
                }
                dismissLoading()
            }
        }
    }

    private fun dismissLoading() {
        if (binding.swipeRefreshNotification.isRefreshing) {
            binding.swipeRefreshNotification.isRefreshing = false
        }
    }

    override fun onRefresh() {
        viewModel.displayNotifications()
    }

}