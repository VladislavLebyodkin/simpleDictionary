package com.example.simpledictionary.database

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {

    fun provideDatabase(application: Application): NotesDatabase {
        return Room.databaseBuilder(application, NotesDatabase::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideNotesDao(database: NotesDatabase): NotesDAO {
        return  database.notesDAO
    }

    single { provideDatabase(androidApplication()) }
    factory { provideNotesDao(get()) }
}
