package com.sejigner.glee.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sejigner.glee.R
import kotlinx.android.synthetic.main.edit_dialog_fragment.*
import kotlinx.android.synthetic.main.save_dialog_fragment.*

class SaveDialog: DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.save_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_yes_save_dialog.setOnClickListener {
            (activity as SaveDialogCallback).save()
            dismiss()
        }

        btn_cancel_save_dialog.setOnClickListener {
            dismiss()
        }
    }

    interface SaveDialogCallback {
        fun save()
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}