package com.herev.diego.marvelcharacters.network

import com.herev.diego.marvelcharacters.domain.model.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MarvelApiService {

    @Headers(
        "Content-Type: application/json"
    )
    @GET("/v1/public/characters")
    suspend fun getCharacters (@Query("offset") offset : Int = 0, @Query("limit") limit : Int = 20) : Response<CharacterDataWrapper>



}