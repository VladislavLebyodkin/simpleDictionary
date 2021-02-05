package com.vlados_project.simpledictionary.addNote.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.addNote.domain.AddNoteInteractor
import com.vlados_project.simpledictionary.base.ValidationInteractor
import com.vlados_project.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.launch

class AddNoteViewModel(
        private val addNoteInteractor: AddNoteInteractor,
        private val validationInteractor: ValidationInteractor) : ViewModel() {

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()
    val isValidInputName = MutableLiveData<Boolean>()
    val isValidInputTranslate = MutableLiveData<Boolean>()

    fun createNote(name: String, translate: String, example: String) {
        viewModelScope.launch {
            try {
                addNoteInteractor.createNote(name, translate, example)
                navController.navigate(R.id.action_addNoteFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }

    fun inputNameTextChanged(text: String) {
        isValidInputName.value = validationInteractor.isNotEmpty(text)
    }

    fun inputTranslateTextChanged(text: String) {
        isValidInputTranslate.value = validationInteractor.isNotEmpty(text)
    }

}