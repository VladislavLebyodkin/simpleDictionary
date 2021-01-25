package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.addNote.domain.AddNoteRepository
import com.example.simpledictionary.noteList.data.local.NotesDao
import com.example.simpledictionary.noteList.data.remote.NotesApi

class AddNoteRepositoryImpl(
        private val notesApi: NotesApi,
        private val notesDao: NotesDao
): AddNoteRepository {

    override suspend fun createNote(name: String, translate: String, example: String): AddNoteResponseDto {
        val newNote = notesApi.createNote(name, translate, example)
        notesDao.insert(newNote.newNote)
        return newNote
    }
}