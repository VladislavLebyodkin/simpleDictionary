package com.vlados_project.simpledictionary.note.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.base.ValidationInteractor
import com.vlados_project.simpledictionary.note.domain.NoteInteractor
import com.vlados_project.simpledictionary.noteList.domain.Note
import com.vlados_project.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NoteViewModel(
        private val note: Note,
        private val noteInteractor: NoteInteractor,
        private val validationInteractor: ValidationInteractor
) : ViewModel() {

    val uiModel = MutableLiveData(note)
    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    val isValidInputName = MutableLiveData<Boolean>()
    val isValidInputTranslate = MutableLiveData<Boolean>()

    fun updateNote(name: String, translate: String, example: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newNote = note.copy(
                    word = name,
                    translate = translate,
                    example = example
                )
                noteInteractor.updateNote(newNote)
                navController.navigateUp()
            } catch (e: Exception) {
                showError.postCall()
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteInteractor.deleteNote(id = note.id)
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