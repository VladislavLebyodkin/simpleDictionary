package com.vlados_project.simpledictionary.register.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.base.ValidationInteractor
import com.vlados_project.simpledictionary.register.domain.RegisterInteractor
import com.vlados_project.simpledictionary.util.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class RegisterViewModel(
        private val registerInteractor: RegisterInteractor,
        private val validationInteractor: ValidationInteractor
        ) : ViewModel() {

    val isValidEmail = MutableLiveData<Boolean>()
    val isValidPassword = MutableLiveData<Boolean>()
    val isValidPasswordConfirm = MutableLiveData<Boolean>()

    lateinit var navController: NavController
    val showError = SingleLiveEvent<Void>()

    fun onRegisterButtonClick(email: String, password: String, passwordConfirm: String) {
        runBlocking {
            try {
                registerInteractor.register(email, password, passwordConfirm)
                navController.navigate(R.id.action_registerFragment_to_noteListFragment)
            } catch (e: Exception) {
                showError.call()
            }
        }
    }

    fun formIsValid(): Boolean {
        return (isValidEmail.value == true) and
                (isValidPassword.value == true) and
                (isValidPasswordConfirm.value == true)
    }

    fun inputEmailTextChanged(text: String) {
        isValidEmail.value = validationInteractor.isCorrectEmail(text)
    }

    fun inputPasswordTextChanged(text: String) {
        isValidPassword.value = validationInteractor.isCorrectPassword(text)
    }

    fun inputPasswordConfirmTextChanged(password: String, passwordConfirm: String) {
        isValidPasswordConfirm.value = validationInteractor.isPasswordsEquals(password, passwordConfirm)
    }
}