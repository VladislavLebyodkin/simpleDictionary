package com.example.simpledictionary.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.register.domain.RegisterInteractor
import com.example.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel(private val interactor: RegisterInteractor) : ViewModel() {

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    fun onRegisterButtonClick(email: String, password: String, passwordConfirm: String) {
        viewModelScope.launch {
            try {
                interactor.register(email, password, passwordConfirm)
                navController.navigate(R.id.action_registerFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }
}