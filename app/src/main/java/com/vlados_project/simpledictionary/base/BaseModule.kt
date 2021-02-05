package com.vlados_project.simpledictionary.base

import org.koin.dsl.module

val baseModule = module {

    single { ValidationInteractor() }

}