package com.example.simpledictionary.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpledictionary.notes.domain.Note
import com.example.simpledictionary.notes.domain.NoteInteractor

class MainViewModel (private val interactor: NoteInteractor) : ViewModel() {

    var notes = liveData {
        emit(interactor.getAllWords())
    }

}