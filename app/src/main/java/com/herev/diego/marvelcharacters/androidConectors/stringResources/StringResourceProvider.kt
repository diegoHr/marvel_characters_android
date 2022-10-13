package com.herev.diego.marvelcharacters.androidConectors.stringResources

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringResourceProvider @Inject constructor(private val context : Context): IStringResourceProvider {

    override fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    override fun getString(@StringRes id: Int, vararg params :Any): String {

        return context.getString(id, *params )
    }
}