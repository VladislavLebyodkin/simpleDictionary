package com.example.simpledictionary.noteList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpledictionary.noteList.domain.NoteListInteractor

class NoteListViewModel (private val interactor: NoteListInteractor) : ViewModel() {

    var notes = liveData {
        emit(interactor.getAllWords())
    }

}