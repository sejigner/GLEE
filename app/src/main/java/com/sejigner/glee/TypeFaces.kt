package com.sejigner.glee

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import java.lang.Exception
import java.util.*

class TypeFaces {
    private val cache: Hashtable<String, Typeface> = Hashtable<String, Typeface>()
    fun getTypeFace(context: Context, assetPath: String?): Typeface? {
        synchronized(cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    val typeFace = Typeface.createFromAsset(
                        context.assets, assetPath
                    )
                    cache.put(assetPath, typeFace)
                } catch (e: Exception) {
                    Log.e("TypeFaces", "Typeface not loaded.")
                    return null
                }
            }
            return cache[assetPath]
        }
    }
}