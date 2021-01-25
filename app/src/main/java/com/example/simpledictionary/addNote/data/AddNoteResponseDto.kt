package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.noteList.data.local.NoteEntity
import com.google.gson.annotations.SerializedName

data class AddNoteResponseDto (
    @SerializedName("data") val newNote: NoteEntity
)