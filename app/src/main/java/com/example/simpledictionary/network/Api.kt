package com.example.simpledictionary.network

import com.example.simpledictionary.addNote.data.NoteAddDto
import com.example.simpledictionary.notes.data.NotesDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @GET("note/list")
    suspend fun getAllWords() : NotesDto

    @POST("note")
    suspend fun createNote(@Body noteAddDto: NoteAddDto)
}