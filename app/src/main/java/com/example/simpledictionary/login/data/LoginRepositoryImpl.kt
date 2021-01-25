package com.example.simpledictionary.login.data

import com.example.simpledictionary.login.domain.LoginRepository
import com.example.simpledictionary.network.AuthApi
import com.example.simpledictionary.util.prefs.UserPrefs

class LoginRepositoryImpl(
        private val authApi: AuthApi,
        private val userPrefs: UserPrefs
) : LoginRepository {

    override suspend fun login(email: String, password: String) {
        val response = authApi.login(null, email, password)
        userPrefs.setAccessToken(response.token)
    }

}