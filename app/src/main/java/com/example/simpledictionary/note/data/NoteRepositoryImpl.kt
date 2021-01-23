package com.example.simpledictionary.note.data

import com.example.simpledictionary.database.NotesDAO
import com.example.simpledictionary.network.Api
import com.example.simpledictionary.note.domain.NoteRepository
import com.example.simpledictionary.noteList.domain.Note

class NoteRepositoryImpl(
    private val api: Api,
    private val notesDB: NotesDAO
): NoteRepository {

    override suspend fun deleteNote(id: Long): EditDeleteNoteResponse {
        val deletedNote = api.deleteNote(id = id)

        notesDB.delete(deletedNote.editedNote.copy(
                id = id
        ))

        return deletedNote
    }

    override suspend fun updateNote(note: Note): EditDeleteNoteResponse {
        val editedNote = api.updateNote(note.id, note.toEditRequestDto())
        notesDB.insert(editedNote.editedNote)
        return editedNote
    }

}