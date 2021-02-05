package com.vlados_project.simpledictionary.register.domain

class RegisterInteractor (private val repository: RegisterRepository) {

    suspend fun register(email: String, password: String, passwordConfirm: String) {
        repository.register(email, password, passwordConfirm)
    }

}