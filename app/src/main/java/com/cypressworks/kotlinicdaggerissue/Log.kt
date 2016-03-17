@file:Suppress("NOTHING_TO_INLINE")

package com.cypressworks.kotlinicdaggerissue

import android.util.Log

val DEBUG: Boolean get() = BuildConfig.DEBUG

interface Taggable {
    val tag: String
}

inline fun Any.log(msg: Any?, priority: Int) {
    if (DEBUG) {
        val method = Thread.currentThread().stackTrace[3].methodName
        val msgString = msg ?: "null"
        val tag = if (this is Taggable) {
            tag
        } else {
            javaClass.simpleName
        }
        Log.println(priority, tag, "$method(): $msgString")
    }
}

fun Any.log(msg: Any?): Unit = log(msg, Log.DEBUG)

inline fun Any.log(lazyMessage: () -> Any?) {
    if (DEBUG) {
        log(lazyMessage())
    }
}