package com.bitnovo.app.log

import com.bitnovo.app.log.loggerDestinations.TestLoggerDestinationImpl
import com.herev.diego.marvelcharacters.logger.Logger
import com.herev.diego.marvelcharacters.logger.LoggerConfiguration

/**
 * Created by Diego Hernando on 13/7/21.
 */
object TestConfigLogger {

    fun configTestEnviorment (){
        Logger._configuration = LoggerConfiguration(listOf(TestLoggerDestinationImpl()), true)
    }
}