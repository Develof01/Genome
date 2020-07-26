package com.mx.kavak.android.gnomegame.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mx.kavak.android.gnomegame.R

fun Activity.setTransparentStatusBar() {
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = Color.TRANSPARENT
    }
}

fun Context.toast(message: String, duration: Int) =
    Toast.makeText(this, message, duration).show()

fun View.chageVisibility(visibility: Int) {
    this.visibility = visibility
}

fun ViewGroup.inflate(resId: Int): View {
    return LayoutInflater.from(this.context).inflate(resId, this, false)
}

fun ImageView.loadImage(id: Int) {
    Glide.with(context)
        .load("https://picsum.photos/id/$id/200/300")
        .error(R.drawable.error_img)
        .into(this)
}