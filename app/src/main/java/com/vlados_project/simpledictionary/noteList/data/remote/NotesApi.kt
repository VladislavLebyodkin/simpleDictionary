package com.vlados_project.simpledictionary.noteList.data.remote

import com.vlados_project.simpledictionary.addNote.data.AddNoteResponseDto
import com.vlados_project.simpledictionary.note.data.EditDeleteNoteDto
import com.vlados_project.simpledictionary.note.data.NoteEditRequestDto
import retrofit2.http.*

interface NotesApi {

    @GET("note/list")
    suspend fun getAllWords(@Query("offset") startPosition: Int) : NotesDto

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
    ): EditDeleteNoteDto

    @DELETE("note/{id}")
    suspend fun deleteNote(@Path("id") id: Long): EditDeleteNoteDto


}