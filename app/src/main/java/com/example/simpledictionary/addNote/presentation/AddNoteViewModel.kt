package com.example.simpledictionary.addNote.presentation

import androidx.lifecycle.ViewModel
import com.example.simpledictionary.addNote.domain.AddNoteInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddNoteViewModel(private val interactor: AddNoteInteractor) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun createNote(name: String, translate: String, example: String) {
        uiScope.launch {
            interactor.createNote(name, translate, example)
        }
    }



}