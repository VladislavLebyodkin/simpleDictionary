package com.vlados_project.simpledictionary.note.data

import com.vlados_project.simpledictionary.noteList.domain.Note
import com.google.gson.annotations.SerializedName

data class NoteEditRequestDto (
    @SerializedName("word") val word: String,
    @SerializedName("translate") val translate: String,
    @SerializedName("example") val example: String? = ""
)

fun Note.toEditRequestDto(): NoteEditRequestDto {
    return NoteEditRequestDto(
            word = word,
            translate = translate,
            example = example
    )
}