package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.addNote.domain.AddNoteRepository
import com.example.simpledictionary.noteList.data.local.NotesDao
import com.example.simpledictionary.noteList.data.remote.NotesApi

class AddNoteRepositoryImpl(
        private val api: NotesApi,
        private val notesDB: NotesDao
): AddNoteRepository {

    override suspend fun createNote(name: String, translate: String, example: String) {
        val newNote = api.createNote(name, translate, example)

        notesDB.insert(newNote.newNote)
    }
}