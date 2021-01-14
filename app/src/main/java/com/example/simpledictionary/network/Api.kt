package com.example.simpledictionary.network

import com.example.simpledictionary.note.data.NoteEditRequestDto
import com.example.simpledictionary.noteList.data.NotesDto
import com.example.simpledictionary.register.domain.UserInfo
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("security/register")
    suspend fun register(
            @Field("email") email: String,
            @Field("pass") password: String,
            @Field("confirm_pass") passwordConfirm: String
    ): UserInfo

    @FormUrlEncoded
    @POST("security/login")
    suspend fun login(
            @Header("YT-AUTH-TOKEN") header: String?,
            @Field("email") email: String,
            @Field("pass") password: String,
    ): UserInfo

    @GET("note/list")
    suspend fun getAllWords() : NotesDto

    @FormUrlEncoded
    @POST("note")
    suspend fun createNote(
            @Field("word") name: String,
            @Field("translate") translate: String,
            @Field("example") example: String
    )

    @PUT("note/{id}")
    suspend fun updateNote(
        @Path("id") id: Long,
        @Body noteEditDto: NoteEditRequestDto
    )

    @DELETE("note/{id}")
    suspend fun deleteNote(@Path("id") id: Long)
}