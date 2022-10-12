package com.herev.diego.marvelcharacters.network

import com.herev.diego.marvelcharacters.utils.JsonPrettyPrinter
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MarvelApiServiceTest {

    val apiService = ApiFactory().createMarvelApiService()


    @Test
    fun `check request returns 200` () {
        runBlocking {
            val response = apiService.getCharacters(10, 1)

            assertThat(response.isSuccessful, CoreMatchers.`is`(true))

            System.out.println(JsonPrettyPrinter().print(response.body()))

        }

    }
}