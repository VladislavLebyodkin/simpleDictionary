package com.vlados_project.simpledictionary.login.domain

class LoginInteractor(private val repository: LoginRepository) {

    suspend fun login(email: String, password: String) {
        repository.login(email, password)
    }

}