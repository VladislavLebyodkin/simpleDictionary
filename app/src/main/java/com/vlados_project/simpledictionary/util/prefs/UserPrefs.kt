package com.vlados_project.simpledictionary.util.prefs

import android.content.SharedPreferences
import com.vlados_project.simpledictionary.util.log

class UserPrefs(private val defPrefs: SharedPreferences) {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val TOKEN_PREFIX = "YourTar "
    }

    fun setAccessToken(token: String) {
        defPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, TOKEN_PREFIX + token).apply()
    }

    fun getAccessToken(): String? {
        return defPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    fun logOut() {
        defPrefs.edit().clear().apply()
    }

}
