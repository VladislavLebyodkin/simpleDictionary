package com.vlados_project.simpledictionary.base

import android.util.Patterns

class ValidationInteractor {

    fun isNotEmpty(text: String): Boolean {
        return text.isNotEmpty()
    }

    fun isCorrectEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isCorrectPassword(password: String): Boolean {
        return password.length >= 3
    }

    fun isPasswordsEquals(password: String, passwordConfirm: String): Boolean {
        return passwordConfirm == password
    }

}