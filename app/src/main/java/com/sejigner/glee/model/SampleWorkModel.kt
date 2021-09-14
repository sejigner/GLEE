package com.sejigner.glee.model

data class SampleWorkModel(
    val title : String,
    val author : String,
    val participationNumber : Int,
    val characterNumber : Int,
    val content : String
) {
    constructor(title: String) : this(title, "", 0,0,"")
}

