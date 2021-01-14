package com.example.simpledictionary.register.data

import com.example.simpledictionary.network.Api
import com.example.simpledictionary.register.domain.RegisterRepository
import com.example.simpledictionary.register.domain.UserInfo
import com.example.simpledictionary.util.prefs.UserPrefs

class RegisterRepositoryImpl(
    private val api: Api,
    private val prefs: UserPrefs
) : RegisterRepository {

    override suspend fun register(email: String, password: String, passwordConfirm: String): UserInfo {
        val response = api.register(email, password, passwordConfirm)
        prefs.setAccessToken(response.token)
        prefs.setUserLoginStatus(true)
        return response
    }
}