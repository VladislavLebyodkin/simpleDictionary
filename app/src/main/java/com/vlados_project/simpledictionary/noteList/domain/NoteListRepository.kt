package com.vlados_project.simpledictionary.noteList.domain

interface NoteListRepository {

    suspend fun getNotes(): List<Note>

    suspend fun loadNotesList(): List<Note>

    fun userIsLogged(): Boolean

    suspend fun logOut()

}