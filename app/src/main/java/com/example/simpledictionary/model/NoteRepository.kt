package com.example.simpledictionary.model

import com.example.simpledictionary.network.Api
import org.koin.dsl.module

val forecastModule = module {
    factory { NoteRepository(get()) }
}

class NoteRepository(private val api: Api) {

    suspend fun getAllWords() = api.getAllWords()

}