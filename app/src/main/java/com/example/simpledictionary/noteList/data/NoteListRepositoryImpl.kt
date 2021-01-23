package com.example.simpledictionary.noteList.data

import com.example.simpledictionary.database.NotesDAO
import com.example.simpledictionary.network.Api
import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListRepository
import com.example.simpledictionary.util.prefs.UserPrefs

class NoteListRepositoryImpl(
    private val api: Api,
    private val prefs: UserPrefs,
    private val notesDB: NotesDAO
): NoteListRepository {

    override fun userIsLogged(): Boolean {
        return prefs.getUserLoginStatus()
    }

    override fun clear() {
        prefs.clear()
    }

    override suspend fun getCachedNotes(): List<Note> {
        return notesDB.getNotesList().asReversed()
    }

    override suspend fun getNotesList(): List<Note> {
        val notes = api.getAllWords()

        notesDB.insertAll(notes.toDB())
        return notes.toDomain()
    }

}