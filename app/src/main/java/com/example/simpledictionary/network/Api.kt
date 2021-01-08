package com.example.simpledictionary.network

import com.example.simpledictionary.noteList.data.NotesDto
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
    
    @PUT("")
    suspend fun updateNote(
            @Field("id") id: Long,
            @Field("word") name: String,
            @Field("translate") translate: String,
            @Field("example") example: String
    )

    @DELETE("note/{id}")
    suspend fun deleteNote(@Path("id") id: Long)
}