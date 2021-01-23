package com.example.simpledictionary.note.domain

import com.example.simpledictionary.note.data.EditDeleteNoteResponse
import com.example.simpledictionary.noteList.domain.Note

interface NoteRepository {

    suspend fun deleteNote(id: Long): EditDeleteNoteResponse

    suspend fun updateNote(note: Note): EditDeleteNoteResponse

}