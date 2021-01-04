package com.example.simpledictionary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.simpledictionary.model.NoteRepository
import com.example.simpledictionary.model.Resp
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

class MainViewModel (private val repository: NoteRepository) : ViewModel() {

    val notes = liveData {
        emit(repository.getAllWords())
    }

}