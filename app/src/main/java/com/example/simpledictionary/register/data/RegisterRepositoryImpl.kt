package com.example.simpledictionary.register.data

import com.example.simpledictionary.network.AuthApi
import com.example.simpledictionary.register.domain.RegisterRepository
import com.example.simpledictionary.register.domain.UserInfo
import com.example.simpledictionary.util.prefs.UserPrefs

class RegisterRepositoryImpl(
        private val authApi: AuthApi,
        private val userPrefs: UserPrefs
) : RegisterRepository {

    override suspend fun register(email: String, password: String, passwordConfirm: String): UserInfo {
        val response = authApi.register(email, password, passwordConfirm)
        userPrefs.setAccessToken(response.token)
        return response
    }
}