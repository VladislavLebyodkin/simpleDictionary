package com.example.simpledictionary.note.domain

import com.example.simpledictionary.addNote.data.AddNoteRepositoryImpl
import com.example.simpledictionary.addNote.domain.AddNoteRepository

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun deleteNote(id: Long) {
        return repository.deleteNote(id)
    }

}