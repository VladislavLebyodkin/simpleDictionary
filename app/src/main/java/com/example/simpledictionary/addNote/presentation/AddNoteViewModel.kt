package com.example.simpledictionary.addNote.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.addNote.domain.AddNoteInteractor
import kotlinx.coroutines.launch

class AddNoteViewModel(private val interactor: AddNoteInteractor) : ViewModel() {

    lateinit var navController: NavController

    fun createNote(name: String, translate: String, example: String) {
        viewModelScope.launch {
            interactor.createNote(name, translate, example)
            navController.navigate(R.id.action_addNoteFragment_to_mainFragment)
        }
    }



}