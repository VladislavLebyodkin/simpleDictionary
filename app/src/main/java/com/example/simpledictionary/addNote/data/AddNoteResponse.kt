package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.noteList.data.remote.NoteDto
import com.google.gson.annotations.SerializedName

data class AddNoteResponse (
    @SerializedName("data") val newNote: NoteDto
    )