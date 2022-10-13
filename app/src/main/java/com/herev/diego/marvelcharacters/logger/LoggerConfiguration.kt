package com.herev.diego.marvelcharacters.logger

import com.bitnovo.app.log.loggerDestinations.AndroidLogcatLoggerDestinyImpl
import com.bitnovo.app.log.loggerDestinations.LoggerDestinationI

open class LoggerConfiguration (val listDestinations : List<LoggerDestinationI>, val debugMode : Boolean = false)
class DefaultLoggerConfiguration () : LoggerConfiguration(listOf(AndroidLogcatLoggerDestinyImpl()))
class DebugLoggerConfiguration () : LoggerConfiguration(listOf(AndroidLogcatLoggerDestinyImpl()), true)