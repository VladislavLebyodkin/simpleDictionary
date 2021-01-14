package com.example.simpledictionary.login.domain

import com.example.simpledictionary.register.domain.UserInfo

interface LoginRepository {

    suspend fun login(email: String, password: String): UserInfo

}