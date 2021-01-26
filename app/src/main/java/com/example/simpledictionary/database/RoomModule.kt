package com.example.simpledictionary.database

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(androidApplication(), NotesDatabase::class.java, "NotesDatabase")
                .build()
    }

}
