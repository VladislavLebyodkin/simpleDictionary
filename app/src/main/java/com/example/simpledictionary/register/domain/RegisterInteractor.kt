package com.example.simpledictionary.register.domain

class RegisterInteractor (private val repository: RegisterRepository) {

    suspend fun register(email: String, password: String, passwordConfirm: String): UserInfo {
        return repository.register(email, password, passwordConfirm)
    }

}