package com.example.simpledictionary.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpledictionary.notes.domain.NoteInteractor

class MainViewModel (private val interactor: NoteInteractor) : ViewModel() {

    val notes = liveData {
        emit(interactor.getAllWords())
    }

}