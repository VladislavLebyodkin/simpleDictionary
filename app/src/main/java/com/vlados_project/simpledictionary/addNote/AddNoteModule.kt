package com.vlados_project.simpledictionary.addNote

import com.vlados_project.simpledictionary.addNote.data.AddNoteRepositoryImpl
import com.vlados_project.simpledictionary.addNote.domain.AddNoteInteractor
import com.vlados_project.simpledictionary.addNote.domain.AddNoteRepository
import com.vlados_project.simpledictionary.addNote.presentation.AddNoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addNoteModule = module {

    viewModel { AddNoteViewModel(get(), get()) }
    factory { AddNoteInteractor(get()) }
    single<AddNoteRepository> { AddNoteRepositoryImpl(get(), get()) }

}