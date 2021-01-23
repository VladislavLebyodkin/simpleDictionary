package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.database.NoteDB
import com.google.gson.annotations.SerializedName

data class AddNoteResponse (
    @SerializedName("data") val newNote: NoteDB
    )