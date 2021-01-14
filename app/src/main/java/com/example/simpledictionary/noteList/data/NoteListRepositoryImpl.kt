package com.example.simpledictionary.noteList.data

import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListRepository
import com.example.simpledictionary.network.Api
import com.example.simpledictionary.util.prefs.UserPrefs

class NoteListRepositoryImpl(
    private val api: Api,
    private val prefs: UserPrefs
): NoteListRepository {

    override fun userIsLogged(): Boolean {
        return prefs.getUserLoginStatus()
    }

    override suspend fun getAllWords(): List<Note> {
        return api.getAllWords().toDomain()
    }

}