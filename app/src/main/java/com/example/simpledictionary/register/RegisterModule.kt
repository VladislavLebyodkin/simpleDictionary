
package com.example.simpledictionary.register

import com.example.simpledictionary.register.data.RegisterRepositoryImpl
import com.example.simpledictionary.register.domain.RegisterInteractor
import com.example.simpledictionary.register.domain.RegisterRepository
import com.example.simpledictionary.register.presentation.RegisterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {

    viewModel { RegisterViewModel(get()) }
    factory { RegisterInteractor(get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get(), get()) }

}