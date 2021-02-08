package com.vlados_project.simpledictionary.addNote.data

import com.vlados_project.simpledictionary.addNote.domain.AddNoteRepository
import com.vlados_project.simpledictionary.noteList.data.local.NotesDao
import com.vlados_project.simpledictionary.noteList.data.remote.NotesApi
import com.vlados_project.simpledictionary.noteList.data.remote.toEntity

class AddNoteRepositoryImpl(
        private val api: NotesApi,
        private val notesDB: NotesDao
): AddNoteRepository {

    override suspend fun createNote(name: String, translate: String, example: String) {
        val newNote = api.createNote(name, translate, example)

        notesDB.insert(newNote.newNote.toEntity())
    }
}