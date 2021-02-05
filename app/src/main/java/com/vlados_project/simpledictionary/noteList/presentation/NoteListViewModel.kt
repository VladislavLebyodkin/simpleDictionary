package com.vlados_project.simpledictionary.noteList.presentation

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.note.presentation.NoteFragment
import com.vlados_project.simpledictionary.noteList.domain.Note
import com.vlados_project.simpledictionary.noteList.domain.NoteListInteractor
import com.vlados_project.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteListViewModel (private val interactor: NoteListInteractor) : ViewModel() {

    lateinit var navController: NavController

    val notes = MutableLiveData<List<Note>>()
    val showError = SingleLiveEvent<Void>()

    fun onViewCreated() {
        if (interactor.isUserLoggedIn()) {
            if(notes.value == null) {
                getNotesList()
                getCachedNotes()
            }
        }
        else {
            viewModelScope.launch {
                delay(1)
                navController.navigate(R.id.action_noteListFragment_to_loginFragment)
            }
        }
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
                showError.call()
            }
        }
    }

    fun onNoteClick(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(NoteFragment.NOTE_PARAMETER, note)
        navController.navigate(R.id.action_noteListFragment_to_noteFragment, bundle)
    }

    fun onShuffleClick() {
        notes.value = notes.value?.shuffled()
    }

    fun onAddNoteClick() {
        navController.navigate(R.id.action_noteListFragment_to_addNoteFragment)
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            interactor.logOut()
            navController.navigate(R.id.action_noteListFragment_to_loginFragment)
        }
    }
}