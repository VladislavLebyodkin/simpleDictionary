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
import com.vlados_project.simpledictionary.util.isCompletedOrCancelled
import com.vlados_project.simpledictionary.util.log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class NoteListViewModel (private val interactor: NoteListInteractor) : ViewModel() {

    lateinit var navController: NavController

    val notes = MutableLiveData<List<Note>>()
    val showError = SingleLiveEvent<Void>()

    init {
        if (interactor.isUserLoggedIn()) {
            viewModelScope.launch {
                interactor.getNotesAsFlow().collect {
                    notes.value = it
                }
            }

            loadNotesList()
        }
        else {
            viewModelScope.launch {
                delay(1)
                navController.navigate(R.id.action_noteListFragment_to_loginFragment)
            }
        }
    }

    var loadingJob: Job? = null

    private fun loadNotesList() {
        loadingJob?.cancel()

        loadingJob = viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    interactor.loadNotesList()
                }
            } catch (e: Exception) {
                showError.postCall()
            }
        }
    }

    fun onScrolled(totalCount: Int, lastVisibleItemPosition: Int) {
        if (loadingJob.isCompletedOrCancelled() && lastVisibleItemPosition + 8 >= totalCount) {
            loadNotesList()
        }
    }

    fun onNoteClick(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(NoteFragment.NOTE_PARAMETER, note)
        navController.navigate(R.id.action_noteListFragment_to_noteFragment, bundle)
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