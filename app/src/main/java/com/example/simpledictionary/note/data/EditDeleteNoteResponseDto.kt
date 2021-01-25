package com.example.simpledictionary.note.data

import com.example.simpledictionary.noteList.data.local.NoteEntity
import com.google.gson.annotations.SerializedName

data class EditDeleteNoteResponseDto (
    @SerializedName("data") val editedNote: NoteEntity
)