package com.example.simpledictionary.addNote.data

import com.example.simpledictionary.addNote.domain.AddNoteRepository
import com.example.simpledictionary.noteList.data.local.NotesDao
import com.example.simpledictionary.noteList.data.remote.NotesApi
<<<<<<< HEAD
import com.example.simpledictionary.noteList.data.remote.toEntity

class AddNoteRepositoryImpl(
        private val api: NotesApi,
        private val notesDB: NotesDao
): AddNoteRepository {

    override suspend fun createNote(name: String, translate: String, example: String) {
        val newNote = api.createNote(name, translate, example)

        notesDB.insert(newNote.newNote.toEntity())
=======

class AddNoteRepositoryImpl(
        private val notesApi: NotesApi,
        private val notesDao: NotesDao
): AddNoteRepository {

    override suspend fun createNote(name: String, translate: String, example: String): AddNoteResponseDto {
        val newNote = notesApi.createNote(name, translate, example)
        notesDao.insert(newNote.newNote)
        return newNote
>>>>>>> dev
    }
}