package com.vlados_project.simpledictionary.note.data

import com.vlados_project.simpledictionary.note.domain.NoteRepository
import com.vlados_project.simpledictionary.noteList.data.local.NotesDao
import com.vlados_project.simpledictionary.noteList.data.remote.NotesApi
import com.vlados_project.simpledictionary.noteList.domain.Note

class NoteRepositoryImpl(
        private val notesApi: NotesApi,
        private val notesDao: NotesDao
): NoteRepository {

    override suspend fun deleteNote(id: Long) {
        notesApi.deleteNote(id = id)
        notesDao.deleteById(id)
    }

    override suspend fun updateNote(note: Note) {
        val editedNote = notesApi.updateNote(note.id, note.toEditRequestDto())
        notesDao.insert(editedNote.editedNote)
    }

}