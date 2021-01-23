package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.addNote.domain.AddNoteRepository
import com.example.simpledictionary.database.NotesDAO
import com.example.simpledictionary.network.Api
import com.example.simpledictionary.util.log

class AddNoteRepositoryImpl(
    private val api: Api,
    private val notesDB: NotesDAO
): AddNoteRepository {

    override suspend fun createNote(name: String, translate: String, example: String): AddNoteResponse {
        val newNote = api.createNote(name, translate, example)
        notesDB.insert(newNote.newNote)
        return newNote
    }
}