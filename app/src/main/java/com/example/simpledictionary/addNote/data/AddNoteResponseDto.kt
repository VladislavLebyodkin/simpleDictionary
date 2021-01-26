package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.noteList.data.local.NoteDto
import com.google.gson.annotations.SerializedName

data class AddNoteResponseDto (
    @SerializedName("data") val newNote: NoteDto
)