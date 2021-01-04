package com.example.simpledictionary.network

import com.example.simpledictionary.model.Resp
import retrofit2.http.GET


interface Api {

    @GET("note/list")
    suspend fun getAllWords() : Resp

}