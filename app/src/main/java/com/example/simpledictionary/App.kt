package com.example.simpledictionary

import android.app.Application
import com.example.simpledictionary.model.forecastModule
import com.example.simpledictionary.network.networkModule
import com.example.simpledictionary.ui.main.fragmentModule
import com.example.simpledictionary.ui.main.viewModelModule
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
                forecastModule,
                viewModelModule,
                fragmentModule))
        }
    }

}