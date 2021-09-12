package com.sejigner.glee.customFont

import android.content.Context
import android.graphics.Typeface
import java.util.*

object FontCache {
    private val fontCache = Hashtable<String, Typeface?>()
    operator fun get(name: String, context: Context): Typeface? {
        var tf = fontCache[name]
        if (tf == null) {
            tf = try {
                Typeface.createFromAsset(context.assets, name)
            } catch (e: Exception) {
                return null
            }
            fontCache[name] = tf
        }
        return tf
    }
}