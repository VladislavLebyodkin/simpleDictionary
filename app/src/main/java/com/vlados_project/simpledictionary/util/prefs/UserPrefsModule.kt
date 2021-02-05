package com.vlados_project.simpledictionary.util.prefs

import org.koin.dsl.module

val userPrefsModule = module {
    single { UserPrefs(get()) }
}