package com.example.simpledictionary.note.data

import com.example.simpledictionary.noteList.domain.Note
import com.google.gson.annotations.SerializedName

data class NoteEditRequestDto (
    @SerializedName("word") val word: String,
    @SerializedName("translate") val translate: String,
    @SerializedName("example") val example: String? = ""
)

fun Note.toEditRequestDto(): NoteEditRequestDto {
    return NoteEditRequestDto(
            word = this.word,
            translate = this.translate,
            example = this.example
    )
}