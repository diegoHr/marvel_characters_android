package com.bitnovo.app.log.loggerDestinations

import android.annotation.SuppressLint
import android.util.Log

/**
 * Created by Diego Hernando on 13/7/21.
 */
@SuppressLint("LogNotTimber")
class AndroidLogcatLoggerDestinyImpl : LoggerDestinationI {

    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

    override fun e(tag: String, th: Throwable) {
        Log.e(tag, th.message, th)
    }

    override fun w(tag: String, message: String) {
        Log.w(tag, message)
    }

    override fun w(tag: String, th: Throwable) {
        Log.w(tag, th.message, th)
    }

    override fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}