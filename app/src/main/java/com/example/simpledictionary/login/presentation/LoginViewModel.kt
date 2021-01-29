package com.example.simpledictionary.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.login.domain.LoginInteractor
import com.example.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(private val interactor: LoginInteractor) : ViewModel() {

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    fun onLoginButtonClick(email: String, password: String) {
        viewModelScope.launch {
            try {
                interactor.login(email, password)
                navController.navigate(R.id.action_loginFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }

}