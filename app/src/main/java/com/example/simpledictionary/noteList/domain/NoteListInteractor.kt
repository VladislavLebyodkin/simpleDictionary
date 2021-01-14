package com.example.simpledictionary.noteList.domain

class NoteListInteractor(private val repository: NoteListRepository) {

    suspend fun getAllWords(): List<Note> {
        return repository.getAllWords()
    }

    fun isUserLogged(): Boolean {
        return repository.userIsLogged()
    }

}