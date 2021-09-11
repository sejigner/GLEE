package com.sejigner.glee.customFont

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EditText
import com.sejigner.glee.R

object CustomFontHelper {
    /**
     * Sets a font on a textview based on the custom com.my.package:font attribute
     * If the custom font attribute isn't found in the attributes nothing happens
     * @param editText
     * @param context
     * @param attrs
     */
    fun setCustomFont(editText: EditText, context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomFont)
        val font = a.getString(R.styleable.CustomFont_font)
        setCustomFont(editText, font, context)
        a.recycle()
    }

    /**
     * Sets a font on a textview
     * @param editText
     * @param font
     * @param context
     */
    fun setCustomFont(editText: EditText, font: String?, context: Context?) {
        if (font == null) {
            return
        }
        val tf: Typeface ?= FontCache.get(font, context!!)
        if (tf != null) {
            editText.typeface = tf
        }
    }
}