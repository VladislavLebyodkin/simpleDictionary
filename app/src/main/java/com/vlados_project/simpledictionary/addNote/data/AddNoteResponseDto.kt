package com.vlados_project.simpledictionary.addNote.data

import com.google.gson.annotations.SerializedName
import com.vlados_project.simpledictionary.noteList.data.remote.NoteDto

data class AddNoteResponseDto (
    @SerializedName("data") val newNote: NoteDto
)