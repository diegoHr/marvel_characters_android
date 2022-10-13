package com.herev.diego.marvelcharacters.utils.images

import android.content.Context

class DensityUtils (val context : Context) {


    fun getDensityImgSuffix(extension : String): String {
        val density = context.resources.displayMetrics.density
        val densitySuffix = when{
            density >= 4.0 -> "fantastic"
            density >= 3.0 -> "xlarge"
            density >= 2.0 -> "large"
            density >= 1.5 -> "medium"
            else -> "small"
        }
        return "standard_$densitySuffix.$extension"
    }
}