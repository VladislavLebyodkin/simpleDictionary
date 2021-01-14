package com.example.simpledictionary.register.domain

interface RegisterRepository {

    suspend fun register(email: String, password: String, passwordConfirm: String): UserInfo

}