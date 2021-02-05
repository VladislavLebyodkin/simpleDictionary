package com.vlados_project.simpledictionary.note.data

import com.vlados_project.simpledictionary.noteList.data.local.NoteDto
import com.google.gson.annotations.SerializedName

data class EditDeleteNoteDto (
    @SerializedName("data") val editedNote: NoteDto
)