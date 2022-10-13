package com.herev.diego.marvelcharacters

import com.bitnovo.app.log.TestConfigLogger

abstract class AbstractTest {

    init {
        TestConfigLogger.configTestEnviorment()
    }
}