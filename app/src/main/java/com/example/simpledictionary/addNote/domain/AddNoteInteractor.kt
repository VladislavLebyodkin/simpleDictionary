package com.example.simpledictionary.addNote.domain

import com.example.simpledictionary.addNote.data.NoteAddDto
import com.example.simpledictionary.notes.data.NoteDto

class AddNoteInteractor(private val repository: AddNoteRepository) {

    suspend fun createNote(noteAddDto: NoteAddDto) {
        repository.createNote(noteAddDto)
    }

}