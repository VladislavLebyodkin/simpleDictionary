package com.vlados_project.simpledictionary.note.domain

import com.vlados_project.simpledictionary.noteList.domain.Note

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun updateNote(note: Note) {
        repository.updateNote(note)
    }

    suspend fun deleteNote(id: Long) {
        repository.deleteNote(id)
    }

}