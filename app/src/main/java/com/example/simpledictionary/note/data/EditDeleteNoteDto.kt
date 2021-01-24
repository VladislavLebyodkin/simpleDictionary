package com.example.simpledictionary.note.data

import com.example.simpledictionary.noteList.data.remote.NoteDto
import com.google.gson.annotations.SerializedName

data class EditDeleteNoteDto (
    @SerializedName("data") val editedNote: NoteDto
)