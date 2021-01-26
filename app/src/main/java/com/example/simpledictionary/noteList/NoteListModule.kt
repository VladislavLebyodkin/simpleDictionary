package com.example.simpledictionary.noteList

import com.example.simpledictionary.database.NotesDatabase
import com.example.simpledictionary.noteList.data.NoteListRepositoryImpl
import com.example.simpledictionary.noteList.domain.NoteListInteractor
import com.example.simpledictionary.noteList.domain.NoteListRepository
import com.example.simpledictionary.noteList.presentation.NoteListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteListModule = module {

    single { get<NotesDatabase>().notesDao }

    viewModel { NoteListViewModel(get()) }
    factory { NoteListInteractor(get()) }
    single<NoteListRepository> { NoteListRepositoryImpl(get(), get(), get()) }


}