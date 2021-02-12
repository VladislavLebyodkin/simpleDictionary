package com.vlados_project.simpledictionary.network

import com.vlados_project.simpledictionary.BuildConfig
import com.vlados_project.simpledictionary.noteList.data.remote.NotesApi
import com.vlados_project.simpledictionary.util.log
import com.vlados_project.simpledictionary.util.prefs.UserPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideNotesApi(get()) }
    single { provideAuthApi(get()) }
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
    val okHttpClient = OkHttpClient.Builder()

    val logInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    okHttpClient.addInterceptor(logInterceptor)

    val headerInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val token = prefs.getAccessToken()

            return if(token != null) {
                return chain.proceed(chain.request()
                    .newBuilder()
                    .addHeader(AUTH_KEY, token)
                    .build())
            } else {
                chain.proceed(request)
            }
        }
    }

    okHttpClient.addInterceptor(headerInterceptor)

    return okHttpClient.build()
}

fun provideNotesApi(retrofit: Retrofit): NotesApi = retrofit.create(NotesApi::class.java)

fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)


