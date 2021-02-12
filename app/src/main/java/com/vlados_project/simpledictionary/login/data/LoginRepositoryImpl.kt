package com.vlados_project.simpledictionary.login.data

import com.vlados_project.simpledictionary.login.domain.LoginRepository
import com.vlados_project.simpledictionary.network.AuthApi
import com.vlados_project.simpledictionary.util.log
import com.vlados_project.simpledictionary.util.prefs.UserPrefs

class LoginRepositoryImpl(
        private val authApi: AuthApi,
        private val userPrefs: UserPrefs
) : LoginRepository {

    override suspend fun login(email: String, password: String) {
        val response = authApi.login(email, password)
        userPrefs.setAccessToken(response.token)
    }

}