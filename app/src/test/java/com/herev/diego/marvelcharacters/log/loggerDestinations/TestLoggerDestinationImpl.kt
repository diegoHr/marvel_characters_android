package com.bitnovo.app.log.loggerDestinations

/**
 * Created by Diego Hernando on 13/7/21.
 */
class TestLoggerDestinationImpl : LoggerDestinationI {
    override fun e(tag: String, message: String) {
        System.out.println("ERROR|$tag -> $message")
    }

    override fun e(tag: String, th: Throwable) {
        th.message?.let{
            e(tag, it)
        }
        th.printStackTrace()
    }

    override fun w(tag: String, message: String) {
        System.out.println("WARNING|$tag -> $message")
    }

    override fun w(tag: String, th: Throwable) {
        th.message?.let{
            w(tag, it)
        }
        th.printStackTrace()
    }

    override fun i(tag: String, message: String) {
        System.out.println("INFO|$tag -> $message")
    }

    override fun d(tag: String, message: String) {
        System.out.println("DEBUG|$tag -> $message")
    }
}