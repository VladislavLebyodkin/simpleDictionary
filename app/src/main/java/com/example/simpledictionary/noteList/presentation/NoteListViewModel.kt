package com.example.simpledictionary.noteList.presentation

import android.os.Bundle
import androidx.compose.runtime.emit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.note.presentation.NoteFragment
import com.example.simpledictionary.noteList.domain.Note
import com.example.simpledictionary.noteList.domain.NoteListInteractor
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteListViewModel (private val interactor: NoteListInteractor) : ViewModel() {

    lateinit var navController: NavController

    val notes = MutableLiveData<List<Note>>()
    val showToast = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()

    init {
        showLoading.value = true
        if (interactor.isUserLogged()) {
            getNotesList()
        }
        else {
            navController.navigate(R.id.action_mainFragment_to_loginFragment)
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

    private fun getNotesList() {
        viewModelScope.launch {
            try {
                notes.value = interactor.getAllWords()
            } catch (e: Exception) {
                showToast.value = e.localizedMessage
            } finally {
                showLoading.value = false
            }
        }
    }

}