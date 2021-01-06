package com.example.simpledictionary.addNote.data

import com.google.gson.annotations.SerializedName

data class NoteAddDto (
    @SerializedName("word") val word: String,
    @SerializedName("translate") val translate: String,
    @SerializedName("example") val example: String = ""
)