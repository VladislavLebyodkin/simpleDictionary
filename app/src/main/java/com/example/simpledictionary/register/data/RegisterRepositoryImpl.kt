package com.example.simpledictionary.register.data

import com.example.simpledictionary.network.AuthApi
import com.example.simpledictionary.register.domain.RegisterRepository
import com.example.simpledictionary.register.domain.UserInfo
import com.example.simpledictionary.util.prefs.UserPrefs

class RegisterRepositoryImpl(
        private val authApi: AuthApi,
<<<<<<< HEAD
        private val userPrefs: UserPrefs
=======
        private val prefs: UserPrefs
>>>>>>> dev
) : RegisterRepository {

    override suspend fun register(email: String, password: String, passwordConfirm: String): UserInfo {
        val response = authApi.register(email, password, passwordConfirm)
<<<<<<< HEAD
        userPrefs.setAccessToken(response.token)
=======
        prefs.setAccessToken(response.token)
>>>>>>> dev
        return response
    }
}