package com.example.simpledictionary.login.data

import com.example.simpledictionary.login.domain.LoginRepository
import com.example.simpledictionary.network.Api
import com.example.simpledictionary.register.domain.UserInfo
import com.example.simpledictionary.util.prefs.UserPrefs

class LoginRepositoryImpl(
    private val api: Api,
    private val prefs: UserPrefs
) : LoginRepository {

    override suspend fun login(email: String, password: String): UserInfo {
        val response = api.login(email, password)
        prefs.setAccessToken(response.token)
        prefs.setUserLoginStatus(true)
        return response
    }

}