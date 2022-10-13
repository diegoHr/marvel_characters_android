package com.herev.diego.marvelcharacters.utils.images

import android.content.Context
import com.herev.diego.marvelcharacters.domain.model.ImageInfo

class ImageUrlGenerator (context: Context) {
    private val densityUtils = DensityUtils(context)

    fun getUrlImage (imageInfo: ImageInfo) : String{
        return "${imageInfo.path}/${densityUtils.getDensityImgSuffix(imageInfo.extension ?: "jpg")}"
    }
}