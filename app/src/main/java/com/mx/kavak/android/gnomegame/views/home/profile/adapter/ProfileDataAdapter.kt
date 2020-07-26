package com.mx.kavak.android.gnomegame.views.home.profile.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mx.kavak.android.domain.models.ProfileData
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.extensions.inflate
import kotlinx.android.synthetic.main.adapter_profile_data.view.*
import kotlin.properties.Delegates

class ProfileDataAdapter(private val context: Context):  RecyclerView.Adapter<ProfileDataViewHolder>() {

    var documentation by Delegates.observable(emptyList<ProfileData>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDataViewHolder {
        return ProfileDataViewHolder(parent.inflate(R.layout.adapter_profile_data))
    }

    override fun getItemCount(): Int {
        return documentation.size
    }

    override fun onBindViewHolder(holder: ProfileDataViewHolder, position: Int) {
        holder.bind(documentation[position], context)
    }

}

class ProfileDataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(profileData: ProfileData, context: Context) {
        with(view) {
            tv_profile_document_title.text = profileData.title
            tv_profile_document_value.text = profileData.data
            setImage(profileData.id, iv_document_status, context)
        }
    }

    fun setImage(id: Int, image: ImageView, context: Context) =
        when(id) {
            0-> {
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_phone))
            }
            1 -> {
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_linkedin))
            }
            else -> {
                image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_github))
            }
        }
}