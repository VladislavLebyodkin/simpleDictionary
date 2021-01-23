package com.example.simpledictionary.addNote.domain

import com.example.simpledictionary.addNote.data.AddNoteResponse

interface AddNoteRepository {

    suspend fun createNote(name: String, translate: String, example: String): AddNoteResponse

}