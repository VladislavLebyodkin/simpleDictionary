package com.example.simpledictionary.network

import com.example.simpledictionary.addNote.data.NoteAddDto
import com.example.simpledictionary.notes.data.NotesDto
import retrofit2.http.*


interface Api {

    @GET("note/list")
    suspend fun getAllWords() : NotesDto

    @FormUrlEncoded
    @POST("note")
    suspend fun createNote(
            @Field("word") name: String,
            @Field("translate") translate: String,
            @Field("example") example: String
    )
}