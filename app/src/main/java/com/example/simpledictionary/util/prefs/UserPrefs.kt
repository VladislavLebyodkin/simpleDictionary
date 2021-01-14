package com.example.simpledictionary.util.prefs

import android.content.SharedPreferences
import org.koin.dsl.module

class UserPrefs(private val defPrefs: SharedPreferences) {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val PREF_KEY_USER_LOGIN_STATUS = "PREF_KEY_USER_LOGIN_STATUS"
        const val PREF_FILE_NAME = "PREF_FILE_NAME"
        const val TOKEN_PREFIX = "YourTar "
    }

    fun setAccessToken(token: String) {
        defPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, TOKEN_PREFIX + token).apply()
    }

    fun getAccessToken(): String? {
        return defPrefs.getString(PREF_KEY_ACCESS_TOKEN, "")
    }

    fun getUserLoginStatus(): Boolean {
        return defPrefs.getBoolean(PREF_KEY_USER_LOGIN_STATUS, false)
    }

    fun setUserLoginStatus(status: Boolean) {
        defPrefs
            .edit()
            .putBoolean(PREF_KEY_USER_LOGIN_STATUS, status)
            .apply()
    }

}
