package com.vlados_project.simpledictionary.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.base.ValidationInteractor
import com.vlados_project.simpledictionary.login.domain.LoginInteractor
import com.vlados_project.simpledictionary.util.SingleLiveEvent
import com.vlados_project.simpledictionary.util.log
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(
        private val loginInteractor: LoginInteractor,
        private val validationInteractor: ValidationInteractor
        ) : ViewModel() {

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    val isValidEmail = MutableLiveData<Boolean>()
    val isValidPassword = MutableLiveData<Boolean>()

    fun onLoginButtonClick(email: String, password: String) {
        runBlocking {
            try {
                loginInteractor.login(email, password)
                navController.navigate(R.id.action_loginFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }

    fun inputEmailTextChanged(text: String) {
        isValidEmail.value = validationInteractor.isCorrectEmail(text)
    }

    fun inputPasswordTextChanged(text: String) {
        isValidPassword.value = validationInteractor.isCorrectPassword(text)
    }

}