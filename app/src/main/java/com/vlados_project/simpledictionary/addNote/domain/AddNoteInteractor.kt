package com.vlados_project.simpledictionary.addNote.domain

class AddNoteInteractor(private val repository: AddNoteRepository) {

    suspend fun createNote(name: String, translate: String, example: String) {
        repository.createNote(name, translate, example)
    }

}