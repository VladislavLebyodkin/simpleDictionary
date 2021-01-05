package com.example.simpledictionary.notes.data

import com.example.simpledictionary.notes.domain.Note
import com.example.simpledictionary.notes.domain.NoteRepository
import com.example.simpledictionary.network.Api

class NoteRepositoryImpl(private val api: Api): NoteRepository {

    override suspend fun getAllWords(): List<Note> {
        return api.getAllWords().toDomain()
    }

}