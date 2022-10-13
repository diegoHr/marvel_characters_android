package com.bitnovo.app.log.loggerDestinations

/**
 * Created by Diego Hernando on 13/7/21.
 */
interface LoggerDestinationI {
    fun e (tag : String, message : String)
    fun e (tag : String, th : Throwable)
    fun w (tag : String, message : String)
    fun w (tag : String, th : Throwable)
    fun i (tag : String, message: String)
    fun d (tag : String, message: String)
}