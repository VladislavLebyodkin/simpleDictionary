package com.example.simpledictionary.notes.domain

interface NoteRepository {

    suspend fun getAllWords(): List<Note>

}