package com.vlados_project.simpledictionary.util

import android.util.Log
import kotlinx.coroutines.Job

fun log(message: Any) {
    Log.d(TAG_DEBUG, message.toString())
}

fun Job?.isCompletedOrCancelled() : Boolean {
    return this == null || isCompleted || isCancelled
}
