package com.example.simpledictionary.login.domain

interface LoginRepository {

    suspend fun login(email: String, password: String)

}