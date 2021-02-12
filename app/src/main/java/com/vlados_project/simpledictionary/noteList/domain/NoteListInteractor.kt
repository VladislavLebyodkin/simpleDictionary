package com.vlados_project.simpledictionary.noteList.domain

import kotlinx.coroutines.flow.Flow

class NoteListInteractor(private val repository: NoteListRepository) {

    suspend fun loadNotesList() {
        repository.loadNotesList()
    }

    suspend fun getNotesAsFlow(): Flow<List<Note>> {
        return repository.getNotes()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.userIsLogged()
    }

    suspend fun logOut() {
        repository.logOut()
    }

}