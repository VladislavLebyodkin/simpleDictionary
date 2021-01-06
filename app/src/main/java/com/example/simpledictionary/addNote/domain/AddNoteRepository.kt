package com.example.simpledictionary.addNote.domain

import com.example.simpledictionary.addNote.data.NoteAddDto

interface AddNoteRepository {

    suspend fun createNote(noteAddDto: NoteAddDto)

}