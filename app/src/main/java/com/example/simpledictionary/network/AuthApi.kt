package com.example.simpledictionary.network

import com.example.simpledictionary.register.domain.UserInfo
import retrofit2.http.*


interface AuthApi {

    @FormUrlEncoded
    @POST("security/register")
    suspend fun register(
            @Field("email") email: String,
            @Field("pass") password: String,
            @Field("confirm_pass") passwordConfirm: String
    ): UserInfo

    @FormUrlEncoded
    @POST("security/login")
    suspend fun login(
            @Header("YT-AUTH-TOKEN") header: String?,
            @Field("email") email: String,
            @Field("pass") password: String,
    ): UserInfo
}