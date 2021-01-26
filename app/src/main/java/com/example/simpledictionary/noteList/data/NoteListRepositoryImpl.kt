package com.example.simpledictionary.noteList.data

import com.example.simpledictionary.noteList.data.local.NotesDao
import com.example.simpledictionary.noteList.data.remote.NotesApi
<<<<<<< HEAD
import com.example.simpledictionary.noteList.data.remote.toDomain
import com.example.simpledictionary.noteList.data.remote.toEntity
=======
import com.example.simpledictionary.noteList.data.remote.toEntity
import com.example.simpledictionary.noteList.data.remote.toDomain
>>>>>>> dev
import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListRepository
import com.example.simpledictionary.util.prefs.UserPrefs

class NoteListRepositoryImpl(
<<<<<<< HEAD
        private val api: NotesApi,
=======
        private val notesApi: NotesApi,
>>>>>>> dev
        private val userPrefs: UserPrefs,
        private val notesDao: NotesDao
): NoteListRepository {

    override fun userIsLogged(): Boolean {
<<<<<<< HEAD
        return userPrefs.getAccessToken() == null
    }

    override fun clear() {
        userPrefs.clear()
    }

    override suspend fun getNotes(): List<Note> {
=======
        return userPrefs.getAccessToken() != null
    }

    override suspend fun getCachedNotes(): List<Note> {
>>>>>>> dev
        return notesDao.getNotesList().asReversed()
    }

    override suspend fun loadNotesList(): List<Note> {
<<<<<<< HEAD
        val notes = api.getAllWords()
=======
        val notes = notesApi.getAllWords()
>>>>>>> dev

        notesDao.insertAll(notes.toEntity())
        return notes.toDomain()
    }

}