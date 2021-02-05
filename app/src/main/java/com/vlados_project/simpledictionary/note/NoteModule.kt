package com.vlados_project.simpledictionary.note

import com.vlados_project.simpledictionary.note.data.NoteRepositoryImpl
import com.vlados_project.simpledictionary.note.domain.NoteInteractor
import com.vlados_project.simpledictionary.note.domain.NoteRepository
import com.vlados_project.simpledictionary.note.presentation.NoteViewModel
import com.vlados_project.simpledictionary.noteList.domain.Note
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteModule = module {

    viewModel { (note: Note) -> NoteViewModel(note, get(), get()) }
    factory { NoteInteractor(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get(), get()) }

}