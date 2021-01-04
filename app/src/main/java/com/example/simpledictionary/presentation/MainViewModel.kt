package com.example.simpledictionary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpledictionary.domain.NoteInteractor
import com.example.simpledictionary.domain.NoteRepository


class MainViewModel (private val interactor: NoteInteractor) : ViewModel() {

    val notes = liveData {
        emit(interactor.getAllWords())
    }

}