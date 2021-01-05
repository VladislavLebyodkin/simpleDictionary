package com.example.simpledictionary.network

import com.example.simpledictionary.notes.data.NotesDto
import retrofit2.http.GET


interface Api {

    @GET("note/list")
    suspend fun getAllWords() : NotesDto

}