package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.addNote.domain.AddNoteRepository
import com.example.simpledictionary.network.Api

class AddNoteRepositoryImpl(private val api: Api): AddNoteRepository {

    override suspend fun createNote(noteAddDto: NoteAddDto) {
        return api.createNote(noteAddDto)
    }
}