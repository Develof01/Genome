package com.mx.kavak.android.gnomegame.ui.home.inhabits.dialogs

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.mx.kavak.android.gnomegame.R
import com.mx.kavak.android.gnomegame.ui.home.inhabits.adapter.ProfessionsAdapter

class ProfessionsDialog(
    activity: Activity,
    private val professions: List<String>): Dialog(activity, R.style.CustomDialog) {

    init {
        setDialogView()
    }


    @SuppressLint("InflateParams")
    private fun setDialogView() {
        val view = layoutInflater.inflate(R.layout.dialog_professions, null)
        setContentView(view)

        val adapter = ProfessionsAdapter()
        val listView = findViewById<RecyclerView>(R.id.lv_items)
        val btnAccept = findViewById<Button>(R.id.btn_accept)
        listView.adapter = adapter
        adapter.items = professions

        btnAccept.setOnClickListener {
            this.dismiss()
        }

    }

}