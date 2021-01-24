package com.example.simpledictionary.noteList.data.remote

import com.example.simpledictionary.noteList.data.local.NoteEntity
import com.example.simpledictionary.noteList.domain.Note
import com.google.gson.annotations.SerializedName

data class NotesDto(
        @SerializedName("data") val notes: List<NoteDto>
)

data class NoteDto(
        @SerializedName("id") val id: Long,
        @SerializedName("word") val word: String,
        @SerializedName("translate") val translate: String,
        @SerializedName("example") val example: String = "",
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("updatedAt") val updatedAt: String,
        @SerializedName("boxNumber") val boxNumber: Int?,
        @SerializedName("lastTransition") val lastTransition: String
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

fun NotesDto.toEntity(): List<NoteEntity> {
    return notes.map {
        NoteEntity(
            id = it.id,
            word = it.word,
            translate = it.translate,
            example = it.example,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt,
            boxNumber = it.boxNumber,
            lastTransition = it.lastTransition
        )
    }
}

fun NoteDto.toEntity(): NoteEntity {
    return NoteEntity(
            id = id,
            word = word,
            translate = translate,
            example = example,
            createdAt = createdAt,
            updatedAt = updatedAt,
            boxNumber = boxNumber,
            lastTransition = lastTransition
    )
}