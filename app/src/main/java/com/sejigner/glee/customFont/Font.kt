package com.sejigner.glee

import android.content.Context
import android.util.AttributeSet
import com.sejigner.glee.customFont.CustomFontHelper


class EditTextPlus : androidx.appcompat.widget.AppCompatEditText {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        CustomFontHelper.setCustomFont(this, context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
        CustomFontHelper.setCustomFont(this, context, attrs)
    }
}