package com.vlados_project.simpledictionary.note.domain

import com.vlados_project.simpledictionary.noteList.domain.Note

interface NoteRepository {

    suspend fun deleteNote(id: Long)

    suspend fun updateNote(note: Note)

}