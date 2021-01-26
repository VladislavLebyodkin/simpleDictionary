package com.example.simpledictionary.noteList.data

import com.example.simpledictionary.noteList.data.local.NotesDao
import com.example.simpledictionary.noteList.data.remote.NotesApi
import com.example.simpledictionary.noteList.data.remote.toDomain
import com.example.simpledictionary.noteList.data.remote.toEntity
import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListRepository
import com.example.simpledictionary.util.prefs.UserPrefs

class NoteListRepositoryImpl(
        private val notesApi: NotesApi,
        private val userPrefs: UserPrefs,
        private val notesDao: NotesDao
): NoteListRepository {

    override fun userIsLogged(): Boolean {
        return userPrefs.getAccessToken() != null
    }

    override suspend fun getNotes(): List<Note> {
        return notesDao.getNotesList().asReversed()
    }

    override suspend fun loadNotesList(): List<Note> {
        val notes = notesApi.getAllWords()

        notesDao.insertAll(notes.toEntity())
        return notes.toDomain()
    }

}