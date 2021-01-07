package com.example.simpledictionary.noteList.data

import com.example.simpledictionary.noteList.domain.Note
import com.google.gson.annotations.SerializedName

data class NotesDto(
        @SerializedName("message") val message: String,
        @SerializedName("data") val notes: List<NoteDto>
)

data class NoteDto(
        @SerializedName("id") val id: Long,
        @SerializedName("word") val word: String,
        @SerializedName("translate") val translate: String,
        @SerializedName("example") val example: String = ""
)

fun NotesDto.toDomain(): List<Note> {
    return notes.map {
        Note(
                id = it.id,
                word = it.word,
                translate = it.translate,
                example = it.example
        )
    }
}
