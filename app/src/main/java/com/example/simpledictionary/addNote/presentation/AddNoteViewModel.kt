package com.example.simpledictionary.addNote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpledictionary.addNote.data.NoteAddDto
import com.example.simpledictionary.addNote.domain.AddNoteInteractor

class AddNoteViewModel(private val interactor: AddNoteInteractor) : ViewModel() {
    var note = liveData<NoteAddDto> {

    }
}