package com.herev.diego.marvelcharacters.domain.model

data class CharacterDataContainer(val offset : Int?, val limit : Int?, val total : Int?,
                                  val count : Int?, val results : List<Character> = listOf() )
