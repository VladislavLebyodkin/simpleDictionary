package com.vlados_project.simpledictionary

import android.app.Application
import com.vlados_project.simpledictionary.addNote.addNoteModule
import com.vlados_project.simpledictionary.base.baseModule
import com.vlados_project.simpledictionary.database.roomModule
import com.vlados_project.simpledictionary.login.loginModule
import com.vlados_project.simpledictionary.network.networkModule
import com.vlados_project.simpledictionary.note.noteModule
import com.vlados_project.simpledictionary.noteList.noteListModule
import com.vlados_project.simpledictionary.register.registerModule
import com.vlados_project.simpledictionary.util.prefs.defaultPrefsModule
import com.vlados_project.simpledictionary.util.prefs.userPrefsModule
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
                roomModule,
                baseModule))
        }
    }

}