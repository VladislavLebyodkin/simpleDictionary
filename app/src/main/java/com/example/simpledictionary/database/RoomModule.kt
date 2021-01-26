package com.example.simpledictionary.database

import android.app.Application
import androidx.room.Room
import com.example.simpledictionary.noteList.data.local.NotesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(androidApplication(), NotesDatabase::class.java, "NotesDatabase")
                .build()
    }

<<<<<<< HEAD
=======
    fun provideNotesDao(database: NotesDatabase): NotesDao {
        return  database.notesDao
    }

    single { provideDatabase(androidApplication()) }
    factory { provideNotesDao(get()) }
>>>>>>> dev
}
