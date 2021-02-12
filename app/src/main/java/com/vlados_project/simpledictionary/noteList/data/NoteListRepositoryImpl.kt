package com.vlados_project.simpledictionary.noteList.data

import com.vlados_project.simpledictionary.noteList.data.local.NotesDao
import com.vlados_project.simpledictionary.noteList.data.remote.NotesApi
import com.vlados_project.simpledictionary.noteList.data.remote.toEntity
import com.vlados_project.simpledictionary.noteList.domain.Note
import com.vlados_project.simpledictionary.noteList.domain.NoteListRepository
import com.vlados_project.simpledictionary.util.prefs.UserPrefs
import kotlinx.coroutines.flow.Flow

class NoteListRepositoryImpl(
        private val notesApi: NotesApi,
        private val userPrefs: UserPrefs,
        private val notesDao: NotesDao
): NoteListRepository {

    companion object {
        private const val OFFSET = 30
    }

    private var startPosition = 0

    override fun userIsLogged(): Boolean {
        return userPrefs.getAccessToken() != null
    }

    override suspend fun logOut() {
        notesDao.clearTable()
        userPrefs.logOut()
        startPosition = 0
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return notesDao.getNotesListAsFlow()
    }

    override suspend fun loadNotesList() {
        startPosition = OFFSET * (notesDao.getNotesList().size / OFFSET)

        val notes = notesApi.getAllWords(startPosition)

        notesDao.insertAll(notes.toEntity())
    }
}