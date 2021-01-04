package com.example.simpledictionary.domain

import com.example.simpledictionary.model.Resp

interface NoteRepository {

    suspend fun getAllWords(): Resp

}