package com.sejigner.glee

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_edit_text.*

class EditTextActivity : AppCompatActivity() {

    var fontSize : Int ?= 30
    var font : String ?= "fonts/hambaksnow.ttf"
    var text : String ?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        val seek = findViewById<SeekBar>(R.id.seek_bar_font_size)


        rb_cafe24SurroundAir_edit.setOnClickListener {
            font = "fonts/cafe24_surround_air.ttf"
            et_edit.typeface = Typeface.createFromAsset(this.assets, "fonts/cafe24_surround_air.ttf")
        }
        rb_aritaBuri_edit.setOnClickListener {
            font = "fonts/arita_buri.otf"
            et_edit.typeface = Typeface.createFromAsset(this.assets, "fonts/arita_buri.otf")
        }
        rb_mapoFlowerIsland_edit.setOnClickListener {
            font = "fonts/mapo_flower_island.ttf"
            et_edit.typeface = Typeface.createFromAsset(this.assets, "fonts/mapo_flower_island.ttf")
        }

        rb_hambaksnow_edit.setOnClickListener {
            et_edit.typeface = Typeface.createFromAsset(this.assets, "fonts/hambaksnow.ttf")
            font = "fonts/hambaksnow.ttf"
        }

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
            }
        })

    }
}