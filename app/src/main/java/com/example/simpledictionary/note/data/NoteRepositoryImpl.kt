package com.example.simpledictionary.note.data

import com.example.simpledictionary.network.Api
import com.example.simpledictionary.note.domain.NoteEditDto
import com.example.simpledictionary.note.domain.NoteRepository
import com.example.simpledictionary.noteList.domain.Note

class NoteRepositoryImpl(private val api: Api): NoteRepository {

    override suspend fun deleteNote(id: Long) {
        return api.deleteNote(id = id)
    }

    override suspend fun updateNote(note: Note) {
        return api.updateNote(note.id, NoteEditDto(note.word, note.translate, note.example.toString()))
    }


//    override suspend fun updateNote(note: Note) {
//        return api.updateNote(
//                id = note.id,
//                name = note.word,
//                translate = note.translate,
//                example = note.example
//        )
//    }

}