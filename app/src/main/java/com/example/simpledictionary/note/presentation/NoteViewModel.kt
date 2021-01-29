package com.example.simpledictionary.note.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.note.domain.NoteInteractor
import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class NoteViewModel(
        private val note: Note,
        private val interactor: NoteInteractor
) : ViewModel() {

    val uiModel = MutableLiveData(note)
    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    fun updateNote(name: String, translate: String, example: String) {
        viewModelScope.launch {
            try {
                val newNote = note.copy(
                    word = name,
                    translate = translate,
                    example = example
                )
                interactor.updateNote(newNote)
                navController.navigate(R.id.action_noteFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }

        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            try {
                interactor.deleteNote(id = note.id)
                navController.navigate(R.id.action_noteFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }
}