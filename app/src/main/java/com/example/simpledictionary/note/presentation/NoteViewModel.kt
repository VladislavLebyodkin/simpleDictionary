package com.example.simpledictionary.note.presentation

import androidx.lifecycle.ViewModel
import com.example.simpledictionary.note.domain.NoteInteractor
import com.example.simpledictionary.noteList.domain.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NoteViewModel(private val interactor: NoteInteractor) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun updateNote(note: Note) {
        scope.launch {
            interactor.updateNote(note)
        }
    }

    fun deleteNote(id: Long) {
        scope.launch {
            interactor.deleteNote(id = id)
        }
    }
}