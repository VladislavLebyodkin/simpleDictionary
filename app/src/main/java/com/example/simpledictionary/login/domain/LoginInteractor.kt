package com.example.simpledictionary.login.domain

class LoginInteractor(private val repository: LoginRepository) {

    suspend fun login(email: String, password: String) {
        repository.login(email, password)
    }

}