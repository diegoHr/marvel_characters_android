package com.herev.diego.marvelcharacters.utils

import java.math.BigInteger
import java.security.MessageDigest

class Md5Utils {

    fun genHash (content : String) : String {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        md.update(content.encodeToByteArray(),0,content.length)
        return BigInteger(1, md.digest()).toString(16)
    }
}