package com.vlados_project.simpledictionary.noteList.domain

import kotlinx.coroutines.flow.Flow

interface NoteListRepository {

    suspend fun getNotes(): Flow<List<Note>>

    suspend fun loadNotesList()

    fun userIsLogged(): Boolean

    suspend fun logOut()

}