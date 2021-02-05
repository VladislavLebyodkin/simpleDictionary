package com.vlados_project.simpledictionary.login

import com.vlados_project.simpledictionary.login.data.LoginRepositoryImpl
import com.vlados_project.simpledictionary.login.domain.LoginInteractor
import com.vlados_project.simpledictionary.login.domain.LoginRepository
import com.vlados_project.simpledictionary.login.presentation.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    viewModel { LoginViewModel(get(), get()) }
    factory { LoginInteractor(get()) }
    single <LoginRepository> { LoginRepositoryImpl(get(), get()) }

}