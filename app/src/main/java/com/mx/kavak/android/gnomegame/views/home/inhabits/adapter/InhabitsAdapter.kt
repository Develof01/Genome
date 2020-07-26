package com.mx.kavak.android.gnomegame.views.home.inhabits.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.extensions.inflate
import com.mx.kavak.android.gnomegame.extensions.loadImage
import kotlinx.android.synthetic.main.adapter_inhabit.view.*
import kotlin.properties.Delegates

class InhabitsAdapter(@NonNull private val listener: (Inhabitant) -> Unit,
                        @NonNull private val favoriteListener: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<InhabitantViewHolder>()  {

    var items by Delegates.observable(emptyList<Inhabitant>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InhabitantViewHolder {
        return InhabitantViewHolder(parent.inflate(R.layout.adapter_inhabit))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: InhabitantViewHolder, position: Int) {
        holder.bind(items[position], listener, favoriteListener)
    }
}

class InhabitantViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(inhabitant: Inhabitant,
             listener: (Inhabitant) -> Unit,
             favListener: (Int, Boolean) -> Unit) {
        with(view) {
            item_image.loadImage(inhabitant.photo)
            item_username.text = inhabitant.name
            if (inhabitant.isFavorite)
                btn_favorite.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
            else
                btn_favorite.setColorFilter(ContextCompat.getColor(context, R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN)
            btn_favorite.setOnClickListener {

            }
            btn_favorite.setOnClickListener { favListener(inhabitant.id, !inhabitant.isFavorite) }
            view.setOnClickListener { listener(inhabitant) }
        }
    }
}