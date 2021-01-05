package com.example.simpledictionary

import com.example.simpledictionary.notes.data.NoteRepositoryImpl
import com.example.simpledictionary.notes.domain.NoteInteractor
import com.example.simpledictionary.notes.domain.NoteRepository
import com.example.simpledictionary.notes.presentation.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(get()) }
    factory { NoteInteractor(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get()) }

}