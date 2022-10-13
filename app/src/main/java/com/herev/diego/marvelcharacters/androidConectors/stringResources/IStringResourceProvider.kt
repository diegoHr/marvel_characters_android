package com.herev.diego.marvelcharacters.androidConectors.stringResources

import androidx.annotation.StringRes


interface IStringResourceProvider {
    fun getString (@StringRes id : Int) : String
    fun getString(@StringRes id : Int, vararg params :Any): String
}