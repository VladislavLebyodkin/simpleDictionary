package com.vlados_project.simpledictionary.login.domain

interface LoginRepository {

    suspend fun login(email: String, password: String)

}