package com.example.simpledictionary.util

import android.util.Log
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