package com.example.simpledictionary.note.data

import com.example.simpledictionary.database.NotesDAO
import com.example.simpledictionary.network.Api
import com.example.simpledictionary.note.domain.NoteRepository
import com.example.simpledictionary.noteList.domain.Note

class NoteRepositoryImpl(
    private val api: Api,
    private val notesDB: NotesDAO
): NoteRepository {

    override suspend fun deleteNote(id: Long) {
        return api.deleteNote(id = id)
    }

    override suspend fun updateNote(note: Note) {
        return api.updateNote(note.id, note.toEditRequestDto())
    }

}