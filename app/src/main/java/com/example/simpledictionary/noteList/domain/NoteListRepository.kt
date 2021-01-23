package com.example.simpledictionary.noteList.domain

interface NoteListRepository {

    suspend fun getCachedNotes(): List<Note>

    suspend fun getNotesList(): List<Note>

    fun userIsLogged(): Boolean

    fun clear()

}