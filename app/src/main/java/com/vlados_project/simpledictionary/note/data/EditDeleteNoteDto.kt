package com.vlados_project.simpledictionary.note.data

import com.google.gson.annotations.SerializedName
import com.vlados_project.simpledictionary.noteList.data.remote.NoteDto

data class EditDeleteNoteDto (
    @SerializedName("data") val editedNote: NoteDto
)