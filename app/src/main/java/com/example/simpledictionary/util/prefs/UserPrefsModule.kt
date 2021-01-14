package com.example.simpledictionary.util.prefs

import org.koin.dsl.module

val userPrefsModule = module {
    single { UserPrefs(get()) }
}