package com.example.simpledictionary.addNote.domain

import com.example.simpledictionary.addNote.data.AddNoteResponse

class AddNoteInteractor(private val repository: AddNoteRepository) {

    suspend fun createNote(name: String, translate: String, example: String): AddNoteResponse {
        return repository.createNote(name, translate, example)
    }

}