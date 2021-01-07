package com.example.simpledictionary.note.data

import com.example.simpledictionary.network.Api
import com.example.simpledictionary.note.domain.NoteRepository

class NoteRepositoryImpl(private val api: Api): NoteRepository {

    override suspend fun deleteNote(id: Long) {
        return api.deleteNote(id = id)
    }

}