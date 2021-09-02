package com.sejigner.glee.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sejigner.glee.CanvasActivity
import com.sejigner.glee.R
import kotlinx.android.synthetic.main.edit_dialog_fragment.*

class EditDialog: DialogFragment() {

    private var content : String?= ""
    private var fontSize : String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = it.getString(EDIT_CONTENT)
            fontSize = it.getString(EDIT_FONT_SIZE)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.edit_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(content=="") {
            tv_notice_edit_dialog.text = "작성된 글이 없습니다. 빈 화면으로 시작할까요?"
            content = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        } else {
            tv_notice_edit_dialog.text = content
        }

        btn_yes_edit_dialog.setOnClickListener {
            val intent = Intent(requireActivity(),CanvasActivity::class.java)
            intent.putExtra("CONTENT",content)
            intent.putExtra("FONT_SIZE",fontSize)
            startActivity(intent)
        }

        btn_no_edit_dialog.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        const val TAG = "EditDialog"
        private const val EDIT_CONTENT = "EDIT_CONTENT"
        private const val EDIT_FONT_SIZE = "EDIT_FONT_SIZE"


        fun newInstance(content: String, fontSize : Int) = EditDialog().apply {
            arguments = Bundle().apply {
                putString(EDIT_CONTENT, content)
                putInt(EDIT_FONT_SIZE, fontSize)
            }
        }
    }

}