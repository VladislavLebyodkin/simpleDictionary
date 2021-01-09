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
import kotlinx.coroutines.launch

class NoteListViewModel (private val interactor: NoteListInteractor) : ViewModel() {

    lateinit var navController: NavController

    var notes = MutableLiveData<List<Note>>()

    init {
        viewModelScope.launch {
            notes.value = interactor.getAllWords()
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

}