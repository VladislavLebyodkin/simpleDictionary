package com.example.simpledictionary.addNote

import com.example.simpledictionary.addNote.data.AddNoteRepositoryImpl
import com.example.simpledictionary.addNote.domain.AddNoteInteractor
import com.example.simpledictionary.addNote.domain.AddNoteRepository
import com.example.simpledictionary.addNote.presentation.AddNoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addNoteModule = module {

    viewModel { AddNoteViewModel(get()) }
    factory { AddNoteInteractor(get()) }
    single<AddNoteRepository> { AddNoteRepositoryImpl(get(), get()) }

}