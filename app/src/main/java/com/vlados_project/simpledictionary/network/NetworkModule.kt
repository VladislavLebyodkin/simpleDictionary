package com.vlados_project.simpledictionary.network

import com.vlados_project.simpledictionary.BuildConfig
import com.vlados_project.simpledictionary.noteList.data.remote.NotesApi
import com.vlados_project.simpledictionary.util.prefs.UserPrefs
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
    factory { provideAuthApi(get()) }
    factory { provideNotesApi(get()) }
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

    val logInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.addInterceptor(logInterceptor)

    val token = prefs.getAccessToken()

    if (token != null) {
        okHttpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader(AUTH_KEY, token)
                    .build()
                return chain.proceed(newRequest)
            }
        })
    }

    return okHttpClient.build()
}

fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

fun provideNotesApi(retrofit: Retrofit): NotesApi = retrofit.create(NotesApi::class.java)

