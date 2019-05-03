package com.ovrbach.coroutinesapplied.util

import android.util.Log

fun Any.logd(message: String) {
    Log.d(this.javaClass.simpleName, message)
}

fun Any.loge(error: Throwable) {
    Log.d(this.javaClass.simpleName, error.localizedMessage)
}