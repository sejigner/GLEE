package com.sejigner.glee.model

import android.net.Uri

data class UserWork (
    val id : Long,
    val name: String,
    val contentUri : Uri
)