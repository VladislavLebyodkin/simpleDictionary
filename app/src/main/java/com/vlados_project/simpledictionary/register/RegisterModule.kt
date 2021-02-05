
package com.vlados_project.simpledictionary.register

import com.vlados_project.simpledictionary.register.data.RegisterRepositoryImpl
import com.vlados_project.simpledictionary.register.domain.RegisterInteractor
import com.vlados_project.simpledictionary.register.domain.RegisterRepository
import com.vlados_project.simpledictionary.register.presentation.RegisterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {

    viewModel { RegisterViewModel(get(), get()) }
    factory { RegisterInteractor(get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get(), get()) }

}