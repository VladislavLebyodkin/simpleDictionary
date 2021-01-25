package com.example.simpledictionary.noteList.data.remote

import com.example.simpledictionary.addNote.data.AddNoteResponseDto
import com.example.simpledictionary.note.data.EditDeleteNoteResponseDto
import com.example.simpledictionary.note.data.NoteEditRequestDto
import retrofit2.http.*

interface NotesApi {

    @GET("note/list")
    suspend fun getAllWords() : NotesDto

    @FormUrlEncoded
    @POST("note")
    suspend fun createNote(
            @Field("word") name: String,
            @Field("translate") translate: String,
            @Field("example") example: String
    ): AddNoteResponseDto

    @PUT("note/{id}")
    suspend fun updateNote(
            @Path("id") id: Long,
            @Body noteEditDto: NoteEditRequestDto
    ): EditDeleteNoteResponseDto

    @DELETE("note/{id}")
    suspend fun deleteNote(@Path("id") id: Long): EditDeleteNoteResponseDto

}