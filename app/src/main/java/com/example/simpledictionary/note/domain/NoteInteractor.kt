package com.example.simpledictionary.note.domain

import com.example.simpledictionary.note.data.EditDeleteNoteResponse
import com.example.simpledictionary.noteList.domain.Note

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun updateNote(note: Note): EditDeleteNoteResponse {
        return repository.updateNote(note)
    }

    suspend fun deleteNote(id: Long): EditDeleteNoteResponse {
        return repository.deleteNote(id)
    }

}