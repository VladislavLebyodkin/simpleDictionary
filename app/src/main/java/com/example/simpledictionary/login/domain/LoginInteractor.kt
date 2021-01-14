package com.example.simpledictionary.login.domain

import com.example.simpledictionary.register.domain.UserInfo

class LoginInteractor(private val repository: LoginRepository) {

    suspend fun login(email: String, password: String): UserInfo {
        return repository.login(email, password)
    }

}