package com.example.simpledictionary.note.domain

import com.example.simpledictionary.note.data.EditDeleteNoteDto
import com.example.simpledictionary.noteList.domain.Note

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun updateNote(note: Note) {
        repository.updateNote(note)
    }

    suspend fun deleteNote(id: Long) {
        repository.deleteNote(id)
    }

}