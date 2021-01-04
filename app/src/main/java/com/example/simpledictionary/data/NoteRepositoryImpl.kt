package com.example.simpledictionary.data

import com.example.simpledictionary.domain.NoteRepository
import com.example.simpledictionary.network.Api
import org.koin.dsl.module

class NoteRepositoryImpl(private val api: Api): NoteRepository {

    override suspend fun getAllWords() = api.getAllWords()

}