package com.example.simpledictionary.noteList.domain

interface NoteListRepository {

    suspend fun getCachedNotes(): List<Note>

    suspend fun loadNotesList(): List<Note>

    fun userIsLogged(): Boolean

}