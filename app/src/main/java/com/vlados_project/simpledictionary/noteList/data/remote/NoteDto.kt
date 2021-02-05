package com.vlados_project.simpledictionary.noteList.data.remote

import com.vlados_project.simpledictionary.noteList.data.local.NoteDto
import com.vlados_project.simpledictionary.noteList.domain.Note
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

fun NotesDto.toEntity(): List<NoteDto> {
    return notes.map {
        NoteDto(
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

fun NoteDto.toEntity(): NoteDto {
    return NoteDto(
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