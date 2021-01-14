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
    factory { provideOkHttpClient(get()) }
    factory { provideApi(get()) }
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

    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(authInterceptor)
        .build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)