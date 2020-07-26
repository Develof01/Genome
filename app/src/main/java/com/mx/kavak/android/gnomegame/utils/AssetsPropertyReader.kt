package com.mx.kavak.android.gnomegame.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.IOException
import java.util.*

class AssetsPropertyReader(private val context: Context) {

    fun getProperty(): Properties? {
        val properties = Properties()
        try {
            val assetManager: AssetManager = context.assets
            val inputStream = assetManager.open("app.properties")
            properties.load(inputStream)
        } catch (e: IOException) {
            Log.e("Genome error: ", e.message, e)
        }
        return properties
    }

}