package com.example.simpledictionary.model

import com.google.gson.annotations.SerializedName


data class Resp(
    val message: String,
    @SerializedName("data") val notes: List<Note>
)

data class Note(
    val id: Long,
    val word: String,
    val translate: String,
    val example: String = ""
)
