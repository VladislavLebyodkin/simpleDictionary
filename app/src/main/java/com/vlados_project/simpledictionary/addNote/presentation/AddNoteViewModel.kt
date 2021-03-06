package com.vlados_project.simpledictionary.addNote.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.addNote.domain.AddNoteInteractor
import com.vlados_project.simpledictionary.base.ValidationInteractor
import com.vlados_project.simpledictionary.noteList.domain.Note
import com.vlados_project.simpledictionary.util.SingleLiveEvent
import com.vlados_project.simpledictionary.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddNoteViewModel(
        private val addNoteInteractor: AddNoteInteractor,
        private val validationInteractor: ValidationInteractor) : ViewModel() {

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()
    val isValidInputName = MutableLiveData<Boolean>()
    val isValidInputTranslate = MutableLiveData<Boolean>()

    fun createNote(name: String, translate: String, example: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addNoteInteractor.createNote(name, translate, example)
                navController.navigateUp()
            } catch (e: Exception) {
                showError.postCall()
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