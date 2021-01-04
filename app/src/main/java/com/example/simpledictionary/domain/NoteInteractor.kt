package com.example.simpledictionary.domain

import com.example.simpledictionary.model.Resp

class NoteInteractor(private val repository: NoteRepository) {

    suspend fun getAllWords(): Resp {
        return repository.getAllWords()
    }

}