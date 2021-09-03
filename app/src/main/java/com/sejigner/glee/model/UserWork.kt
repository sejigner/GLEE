package com.sejigner.glee.model

import android.graphics.Bitmap
import android.net.Uri

data class UserWork (
    val id : Long,
    val name: String,
    val contentUri : Uri
)