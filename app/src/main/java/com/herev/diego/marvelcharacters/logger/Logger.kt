package com.herev.diego.marvelcharacters.logger

import com.google.gson.GsonBuilder
import com.herev.diego.marvelcharacters.BuildConfig
import com.herev.diego.marvelcharacters.network.NetResponseProcessor

object Logger  {
    internal var _configuration : LoggerConfiguration = if(BuildConfig.DEBUG) DebugLoggerConfiguration() else DefaultLoggerConfiguration()
    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun e (tag : String, message : String){
        _configuration.listDestinations.forEach {
            it.e(tag, message)
        }
    }
    fun e (tag : String, th : Throwable){
        _configuration.listDestinations.forEach {
            it.e(tag, th)
        }
    }
    fun w (tag : String, message : String){
        _configuration.listDestinations.forEach {
            it.w(tag, message)
        }
    }
    fun w (tag : String, th : Throwable){
        _configuration.listDestinations.forEach {
            it.w(tag, th)
        }
    }
    fun i (tag : String, message: String){
        _configuration.listDestinations.forEach {
            it.i(tag, message)
        }
    }
    fun d (tag : String, message: String){
        if(_configuration.debugMode) {
            _configuration.listDestinations.forEach {
                it.d(tag, message)
            }
        }
    }

    fun logErrorBit (tag : String, error : NetResponseProcessor.ApiResponseErrorException){
        val errorJson = gson.toJson(error)
        e(tag, "Api response error -> $errorJson")
    }

}