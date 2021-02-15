package com.vlados_project.simpledictionary.noteList

import com.vlados_project.simpledictionary.database.NotesDatabase
import com.vlados_project.simpledictionary.noteList.data.NoteListRepositoryImpl
import com.vlados_project.simpledictionary.noteList.domain.NoteListInteractor
import com.vlados_project.simpledictionary.noteList.domain.NoteListRepository
import com.vlados_project.simpledictionary.noteList.presentation.NoteListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteListModule = module {

    single { get<NotesDatabase>().notesDao }

    viewModel { NoteListViewModel(get()) }
    factory { NoteListInteractor(get()) }
    single<NoteListRepository> { NoteListRepositoryImpl(get(), get(), get()) }

}