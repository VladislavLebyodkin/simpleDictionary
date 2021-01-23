package com.example.simpledictionary.util

import android.util.Log
import android.util.Patterns
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

fun log(message: Any) {
    Log.d(TAG_DEBUG, message.toString())
}

fun EditText.validate(message: String, validator: (String) -> Boolean) {
    this.doAfterTextChanged {
        this.error = if (validator(it.toString())) null else message
    }

    this.error = if (validator(this.text.toString())) null else message
}

fun String.isNotEmptyField(): Boolean
        = this.isNotEmpty()

fun String.isValidEmail(): Boolean
        = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean
        = this.length >= 3