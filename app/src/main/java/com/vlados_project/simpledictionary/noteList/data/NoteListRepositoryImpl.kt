package com.vlados_project.simpledictionary.noteList.data

import com.vlados_project.simpledictionary.noteList.data.local.NotesDao
import com.vlados_project.simpledictionary.noteList.data.remote.NotesApi
import com.vlados_project.simpledictionary.noteList.data.remote.toDomain
import com.vlados_project.simpledictionary.noteList.data.remote.toEntity
import com.vlados_project.simpledictionary.noteList.domain.Note
import com.vlados_project.simpledictionary.noteList.domain.NoteListRepository
import com.vlados_project.simpledictionary.util.log
import com.vlados_project.simpledictionary.util.prefs.UserPrefs
import kotlinx.coroutines.flow.Flow

class NoteListRepositoryImpl(
        private val notesApi: NotesApi,
        private val userPrefs: UserPrefs,
        private val notesDao: NotesDao
): NoteListRepository {

    override fun userIsLogged(): Boolean {
        return userPrefs.getAccessToken() != null
    }

    override suspend fun logOut() {
        notesDao.clearTable()
        userPrefs.logOut()
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return notesDao.getNotesList()
    }

    override suspend fun loadNotesList(): List<Note> {
        val notes = notesApi.getAllWords()
        log(notes.message)

        notesDao.updateAll(notes.toEntity())
        return notes.toDomain()
    }

}