package com.sejigner.glee

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import com.sejigner.glee.customFont.CustomFontHelper
import com.sejigner.glee.fragment.EditDialog
import kotlinx.android.synthetic.main.activity_canvas.*
import kotlinx.android.synthetic.main.activity_edit_text.*
import java.lang.Exception
import java.util.*

class EditTextActivity : AppCompatActivity(), EditDialog.EditDialogListener {

    var fontSize: Int? = 30
    var text: String? = ""
    var hasWritten : Boolean = false

    companion object {
        const val CAFE24_SURROUND_AIR = "fonts/cafe24_surround_air.ttf"
        const val ARITA_BURI = "fonts/arita_buri.otf"
        const val MAPO_FLOWER = "fonts/mapo_flower_island.ttf"
        const val HAMBAK_SNOW = "fonts/hambaksnow.ttf"
        const val CAFE24_SHINING_STAR = "fonts/cafe24_shining_star.ttf"
        const val MIDDLE_SCHOOL_STUDENT = "fonts/middle_school_student.ttf"
        const val NANUM_BARUN_PEN = "fonts/nanum_barun_pen.ttf"
        const val NANUM_PEN = "fonts/nanum_pen.ttf"
        const val BM_EUJIRO = "fonts/bm_euljiro.ttf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        val seek = findViewById<SeekBar>(R.id.seek_bar_guide_size)

        iv_close_edit.setOnClickListener {
            super.onBackPressed()
        }

        tv_start.setOnClickListener {
            var preview : String ?= null
            if(text!!.isNotEmpty()) {
                preview = if(text!!.length > 20) {
                    text!!.substring(0..20)
                } else text
                hasWritten = true
            } else preview =""
            EditDialog.newInstance(preview!!).show(this@EditTextActivity.supportFragmentManager, EditDialog.TAG)
        }

        setFontOnClickListener()

        seek?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var progressChanged = 0
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progressChanged = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                progressChanged = seek.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                et_edit.textSize = seek.progress.toFloat()
                fontSize = seek.progress
            }
        })

        et_edit?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                tv_count_character.text = getString(R.string.count_character)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val userInput = et_edit.text.toString()
                tv_count_character.text = userInput.length.toString() + " / 100"
            }

            override fun afterTextChanged(s: Editable?) {
                val userInput = et_edit.text.toString()
                tv_count_character.text = userInput.length.toString() + " / 100"
                text = et_edit.text.toString()
            }
        })

    }
    private fun setFontOnClickListener() {
        rb_cafe24SurroundAir_edit.setOnClickListener {
            replaceFont(CAFE24_SURROUND_AIR)
        }
        rb_aritaBuri_edit.setOnClickListener {
            replaceFont(ARITA_BURI)
        }
        rb_mapoFlowerIsland_edit.setOnClickListener {
            replaceFont(MAPO_FLOWER)
        }
        rb_hambaksnow_edit.setOnClickListener {
            replaceFont(HAMBAK_SNOW)
        }
        rb_cafe24_shining_star_edit.setOnClickListener {
            replaceFont(CAFE24_SHINING_STAR)
        }
        rb_nanum_barun_pen_edit.setOnClickListener {
            replaceFont(NANUM_BARUN_PEN)
        }
        rb_nanum_pen_edit.setOnClickListener {
            replaceFont(NANUM_PEN)
        }
        rb_middle_school_student_edit.setOnClickListener {
            replaceFont(MIDDLE_SCHOOL_STUDENT)
        }
        rb_bm_euljiro_edit.setOnClickListener {
            replaceFont(BM_EUJIRO)
        }
    }

    private fun replaceFont(font: String) {
        // et_edit.typeface = Typeface.createFromAsset(this.assets, font)
        CustomFontHelper.setCustomFont(et_edit,font,this)
    }

    override fun runCanvasActivity() {
        val intent = Intent(this@EditTextActivity,CanvasActivity::class.java)
        intent.putExtra("CONTENT",text + "\n\n\n")
        intent.putExtra("HAS_WRITTEN", hasWritten)
        startActivity(intent)
    }
}