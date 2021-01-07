package com.example.simpledictionary.noteList.data

import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListRepository
import com.example.simpledictionary.network.Api

class NoteListRepositoryImpl(private val api: Api): NoteListRepository {

    override suspend fun getAllWords(): List<Note> {
        return api.getAllWords().toDomain()
    }

}