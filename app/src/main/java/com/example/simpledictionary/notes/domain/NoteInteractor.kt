package com.example.simpledictionary.notes.domain

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun getAllWords(): List<Note> {
        return repository.getAllWords()
    }

}