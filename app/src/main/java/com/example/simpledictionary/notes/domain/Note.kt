package com.example.simpledictionary.notes.domain

data class Note(
        val id: Long,
        val word: String,
        val translate: String,
        val example: String? = ""
)
