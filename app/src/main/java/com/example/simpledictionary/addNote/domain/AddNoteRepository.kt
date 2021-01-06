package com.example.simpledictionary.addNote.domain

interface AddNoteRepository {

    suspend fun createNote(name: String, translate: String, example: String)

}