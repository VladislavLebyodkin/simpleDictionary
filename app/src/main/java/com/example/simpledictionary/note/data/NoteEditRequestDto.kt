package com.example.simpledictionary.note.domain

import com.google.gson.annotations.SerializedName

data class NoteEditDto (
    @SerializedName("word") val word: String,
    @SerializedName("translate") val translate: String,
    @SerializedName("example") val example: String = ""
)