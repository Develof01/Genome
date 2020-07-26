package com.mx.kavak.android.gnomegame.views.home.inhabits.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.extensions.inflate
import kotlinx.android.synthetic.main.adapter_profession.view.*
import kotlin.properties.Delegates

class ProfessionsAdapter:  RecyclerView.Adapter<ProfessionsViewHolder>()  {

    var items by Delegates.observable(emptyList<String>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionsViewHolder {
        return ProfessionsViewHolder(parent.inflate(R.layout.adapter_profession))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProfessionsViewHolder, position: Int) {
        holder.bind(items[position])
    }

}

class ProfessionsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(profession: String) {
        with(view) {
            tv_profession_name.text = profession
        }
    }
}