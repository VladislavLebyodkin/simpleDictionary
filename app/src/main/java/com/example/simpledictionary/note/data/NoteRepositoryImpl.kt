package com.example.simpledictionary.note.data

import com.example.simpledictionary.noteList.data.local.NotesDao
<<<<<<< HEAD
=======
import com.example.simpledictionary.noteList.data.remote.NotesApi
>>>>>>> dev
import com.example.simpledictionary.note.domain.NoteRepository
import com.example.simpledictionary.noteList.data.remote.NotesApi
import com.example.simpledictionary.noteList.data.remote.toEntity
import com.example.simpledictionary.noteList.domain.Note

class NoteRepositoryImpl(
<<<<<<< HEAD
        private val api: NotesApi,
=======
        private val notesApi: NotesApi,
>>>>>>> dev
        private val notesDao: NotesDao
): NoteRepository {

    override suspend fun deleteNote(id: Long) {
<<<<<<< HEAD
//        val deletedNote = api.deleteNote(id = id)
//        notesDB.delete(deletedNote.editedNote.copy(
//                id = id
//        ).toEntity())

        api.deleteNote(id)
=======
        notesApi.deleteNote(id = id)
>>>>>>> dev
        notesDao.deleteById(id)
    }

    override suspend fun updateNote(note: Note) {
<<<<<<< HEAD
        val editedNote = api.updateNote(note.id, note.toEditRequestDto())
        notesDao.insert(editedNote.editedNote.toEntity())
=======
        val editedNote = notesApi.updateNote(note.id, note.toEditRequestDto())
        notesDao.insert(editedNote.editedNote)
>>>>>>> dev
    }

}