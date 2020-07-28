package com.mx.kavak.android.gnomegame.ui.home.notifications.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.kavak.android.domain.models.Notification
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.extensions.inflate
import kotlinx.android.synthetic.main.adapter_notification.view.*
import kotlin.properties.Delegates

class NotificationAdapter: RecyclerView.Adapter<NotificationViewHolder>() {

    var notifications by Delegates.observable(emptyList<Notification>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(parent.inflate(R.layout.adapter_notification))
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

}


class NotificationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(notification: Notification) {
        with(view) {
            tv_notification_title.text = notification.title
            tv_notification_message.text = notification.body
        }
    }
}