package com.example.simpledictionary.note.data

import com.example.simpledictionary.noteList.data.local.NotesDao
import com.example.simpledictionary.note.domain.NoteRepository
import com.example.simpledictionary.noteList.data.remote.NotesApi
import com.example.simpledictionary.noteList.data.remote.toEntity
import com.example.simpledictionary.noteList.domain.Note

class NoteRepositoryImpl(
        private val api: NotesApi,
        private val notesDao: NotesDao
): NoteRepository {

    override suspend fun deleteNote(id: Long) {
//        val deletedNote = api.deleteNote(id = id)
//        notesDB.delete(deletedNote.editedNote.copy(
//                id = id
//        ).toEntity())

        api.deleteNote(id)
        notesDao.deleteById(id)
    }

    override suspend fun updateNote(note: Note) {
        val editedNote = api.updateNote(note.id, note.toEditRequestDto())
        notesDao.insert(editedNote.editedNote.toEntity())
    }

}