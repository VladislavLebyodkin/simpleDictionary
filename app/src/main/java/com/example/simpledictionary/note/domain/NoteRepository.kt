package com.example.simpledictionary.note.domain

import com.example.simpledictionary.note.data.EditNoteResponse
import com.example.simpledictionary.noteList.domain.Note

interface NoteRepository {

    suspend fun deleteNote(id: Long): EditNoteResponse

    suspend fun updateNote(note: Note): EditNoteResponse

}