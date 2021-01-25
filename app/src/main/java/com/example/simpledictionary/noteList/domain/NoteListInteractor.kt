package com.example.simpledictionary.noteList.domain

class NoteListInteractor(private val repository: NoteListRepository) {

    suspend fun getNotesList(): List<Note> {
        return repository.loadNotesList()
    }

    suspend fun getCachedNotesList(): List<Note> {
        return repository.getCachedNotes()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.userIsLogged()
    }

}