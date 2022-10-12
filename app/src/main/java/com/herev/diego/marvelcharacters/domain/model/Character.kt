package com.herev.diego.marvelcharacters.domain.model

import java.util.*

data class Character (val id : Int?, val name : String?, val description : String?,
                      val modified : Date, val thumbnail : ImageInfo?,
                      val urls : List<UrlInfo>? = listOf() )
