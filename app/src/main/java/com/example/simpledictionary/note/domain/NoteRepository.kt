package com.example.simpledictionary.note.domain

interface NoteRepository {

    suspend fun deleteNote(id: Long)

}