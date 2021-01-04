package com.example.simpledictionary

import com.example.simpledictionary.data.NoteRepositoryImpl
import com.example.simpledictionary.domain.NoteInteractor
import com.example.simpledictionary.domain.NoteRepository
import com.example.simpledictionary.presentation.MainViewModel
import org.koin.dsl.module

val mainModule = module {

    factory { MainViewModel(get()) }
    factory { NoteInteractor(get()) }
    single<NoteRepository> { NoteRepositoryImpl(get()) }

}