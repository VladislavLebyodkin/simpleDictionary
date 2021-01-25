package com.example.simpledictionary.note.domain

import com.example.simpledictionary.noteList.domain.Note

interface NoteRepository {

    suspend fun deleteNote(id: Long)

    suspend fun updateNote(note: Note)

}