package com.example.simpledictionary

import android.app.Application
import com.example.simpledictionary.addNote.addNoteModule
import com.example.simpledictionary.database.roomModule
import com.example.simpledictionary.login.loginModule
import com.example.simpledictionary.network.networkModule
import com.example.simpledictionary.note.noteModule
import com.example.simpledictionary.noteList.noteListModule
import com.example.simpledictionary.register.registerModule
import com.example.simpledictionary.util.prefs.defaultPrefsModule
import com.example.simpledictionary.util.prefs.userPrefsModule
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
                defaultPrefsModule,
                userPrefsModule,
                registerModule,
                loginModule,
                noteListModule,
                addNoteModule,
                noteModule,
                roomModule))
        }
    }

}