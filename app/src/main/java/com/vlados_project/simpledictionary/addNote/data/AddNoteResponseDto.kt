package com.vlados_project.simpledictionary.addNote.data

import com.vlados_project.simpledictionary.noteList.data.local.NoteDto
import com.google.gson.annotations.SerializedName

data class AddNoteResponseDto (
    @SerializedName("data") val newNote: NoteDto
)