package com.example.simpledictionary.noteList.domain

interface NoteListRepository {

    suspend fun getAllWords(): List<Note>

}