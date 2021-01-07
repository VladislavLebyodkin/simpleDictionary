package com.example.simpledictionary.note.presentation

import androidx.lifecycle.ViewModel
import com.example.simpledictionary.note.domain.NoteInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NoteViewModel(private val interactor: NoteInteractor) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteNote(id: Long) {
        scope.launch {
            interactor.deleteNote(id = id)
        }
    }

}