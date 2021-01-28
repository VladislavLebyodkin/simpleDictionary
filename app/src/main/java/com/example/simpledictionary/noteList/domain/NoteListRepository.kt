package com.example.simpledictionary.noteList.domain

interface NoteListRepository {

    suspend fun getNotes(): List<Note>

    suspend fun loadNotesList(): List<Note>

    fun userIsLogged(): Boolean

    fun logOut()

}