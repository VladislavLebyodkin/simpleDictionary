package com.vlados_project.simpledictionary.register.data

import com.vlados_project.simpledictionary.network.AuthApi
import com.vlados_project.simpledictionary.register.domain.RegisterRepository
import com.vlados_project.simpledictionary.register.domain.UserInfo
import com.vlados_project.simpledictionary.util.prefs.UserPrefs

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