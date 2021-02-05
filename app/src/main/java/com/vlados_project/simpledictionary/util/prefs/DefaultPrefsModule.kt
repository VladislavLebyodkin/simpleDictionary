package com.vlados_project.simpledictionary.util.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val defaultPrefsModule = module {
    single { provideSharedPreferences(androidContext()) }
}

private fun provideSharedPreferences(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}