package com.example.simpledictionary.note

import com.example.simpledictionary.note.data.NoteRepositoryImpl
import com.example.simpledictionary.note.domain.NoteInteractor
import com.example.simpledictionary.note.domain.NoteRepository
import com.example.simpledictionary.note.presentation.NoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteModule = module {

    viewModel { NoteViewModel(get()) }
    factory { NoteInteractor(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get()) }

}