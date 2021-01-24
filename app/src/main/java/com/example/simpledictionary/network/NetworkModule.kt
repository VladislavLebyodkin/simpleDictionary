package com.example.simpledictionary.network

import com.example.simpledictionary.BuildConfig
import com.example.simpledictionary.util.prefs.UserPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideApi(get()) }
    single { provideRetrofit(get()) }
}

const val AUTH_KEY = "YT-AUTH-TOKEN"

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(prefs: UserPrefs): OkHttpClient {

    val authInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest: Request = chain.request().newBuilder()
                .addHeader(AUTH_KEY, prefs.getAccessToken()!!)
                .build()
            return chain.proceed(newRequest)
        }
    }

    val logInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.addInterceptor(logInterceptor)

    if(prefs.getAccessToken() != null) {
        okHttpClient.addInterceptor(authInterceptor)
    }

    return okHttpClient.build()
}

fun provideApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)