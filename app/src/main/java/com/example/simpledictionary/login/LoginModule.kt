package com.example.simpledictionary.login

import com.example.simpledictionary.login.data.LoginRepositoryImpl
import com.example.simpledictionary.login.domain.LoginInteractor
import com.example.simpledictionary.login.domain.LoginRepository
import com.example.simpledictionary.login.presentation.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    viewModel { LoginViewModel(get()) }
    factory { LoginInteractor(get()) }
    single <LoginRepository> { LoginRepositoryImpl(get(), get()) }

}