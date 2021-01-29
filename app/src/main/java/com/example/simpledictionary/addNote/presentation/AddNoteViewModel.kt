package com.example.simpledictionary.addNote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.addNote.domain.AddNoteInteractor
import com.example.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.launch

class AddNoteViewModel(private val interactor: AddNoteInteractor) : ViewModel() {

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    fun createNote(name: String, translate: String, example: String) {
        viewModelScope.launch {
            try {
                interactor.createNote(name, translate, example)
                navController.navigate(R.id.action_addNoteFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }



}