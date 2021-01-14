package com.example.simpledictionary.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.simpledictionary.R
import com.example.simpledictionary.login.domain.LoginInteractor
import com.example.simpledictionary.util.log
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val interactor: LoginInteractor) : ViewModel() {

    lateinit var navController: NavController

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                interactor.login(email, password)
                navController.navigate(R.id.action_loginFragment_to_mainFragment)
            } catch (e: Exception) {
                log("Error \n${e.localizedMessage}")
            }
        }
    }

}