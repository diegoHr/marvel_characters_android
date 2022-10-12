package com.herev.diego.marvelcharacters.utils

import com.google.gson.GsonBuilder


class JsonPrettyPrinter  {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun <E> print (elementToPrint : E) : String{
        return gson.toJson(elementToPrint)
    }
}