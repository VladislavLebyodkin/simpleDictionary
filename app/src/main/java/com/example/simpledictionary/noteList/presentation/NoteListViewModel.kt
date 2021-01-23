package com.example.simpledictionary.noteList.presentation

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.note.presentation.NoteFragment
import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListInteractor
import com.example.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteListViewModel (private val interactor: NoteListInteractor) : ViewModel() {

    lateinit var navController: NavController

    val notes = MutableLiveData<List<Note>>()
    val showError = SingleLiveEvent<Boolean>()

    init {
        if (interactor.isUserLoggedIn()) {
            getCachedNotes()
            getNotesList()
        }
        else {
//            navController.navigate(R.id.action_mainFragment_to_loginFragment)

            viewModelScope.launch {
                delay(1)
                navController.navigate(R.id.action_mainFragment_to_loginFragment)
            }
        }
    }

    fun onNoteClick(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(NoteFragment.NOTE_PARAMETER, note)
        navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
    }

    fun onShuffleClick() {
        notes.value = notes.value?.shuffled()
    }

    private fun getCachedNotes() {
        viewModelScope.launch {
            notes.value = interactor.getCachedNotesList()
        }
    }

    private fun getNotesList() {
        viewModelScope.launch {
            try {
                notes.value = interactor.getNotesList()
            } catch (e: Exception) {
                showError.value = true
            }
        }
    }
}