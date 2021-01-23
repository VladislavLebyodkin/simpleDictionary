package com.example.simpledictionary.note.data

import com.example.simpledictionary.database.NoteDB
import com.google.gson.annotations.SerializedName

data class EditNoteResponse (
    @SerializedName("data") val editedNote: NoteDB
)