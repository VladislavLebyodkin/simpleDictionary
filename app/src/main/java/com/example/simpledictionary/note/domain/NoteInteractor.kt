package com.example.simpledictionary.note.domain

import com.example.simpledictionary.note.data.EditNoteResponse
import com.example.simpledictionary.noteList.domain.Note

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun updateNote(note: Note): EditNoteResponse {
        return repository.updateNote(note)
    }

    suspend fun deleteNote(id: Long): EditNoteResponse {
        return repository.deleteNote(id)
    }

}