package com.example.simpledictionary.addNote.domain

import com.example.simpledictionary.addNote.data.AddNoteResponseDto

interface AddNoteRepository {

    suspend fun createNote(name: String, translate: String, example: String): AddNoteResponseDto

}