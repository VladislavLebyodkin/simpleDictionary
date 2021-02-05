package com.vlados_project.simpledictionary.register.domain

interface RegisterRepository {

    suspend fun register(email: String, password: String, passwordConfirm: String): UserInfo

}