package com.example.simpledictionary

import android.app.Application
import com.example.simpledictionary.addNote.addNoteModule
import com.example.simpledictionary.network.networkModule
import com.example.simpledictionary.note.noteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                networkModule,
                mainModule,
                addNoteModule,
                noteModule))
        }
    }

}