package com.herev.diego.marvelcharacters.network

import com.herev.diego.marvelcharacters.AbstractTest
import com.herev.diego.marvelcharacters.utils.JsonPrettyPrinter
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test

class MarvelApiServiceTest : AbstractTest() {

    val apiService = ApiFactory().createMarvelApiService()
    val netResponseProcessor = NetResponseProcessor()


    @Test
    fun `check request returns 200` () {
        runBlocking {
            val response = apiService.getCharacters(10, 1)

            assertThat(response.isSuccessful, `is`(true))

            System.out.println(JsonPrettyPrinter().print(response.body()))

        }

    }

    @Test
    fun `check request returns 409` () {
        runBlocking {
            try {
                netResponseProcessor.checkResponse(apiService.getCharacters(10, 200))
                Assert.fail()
            }catch (th : Throwable){
                assertThat(th, CoreMatchers.instanceOf(NetResponseProcessor.ApiResponseErrorException::class.java))
                val apiResponseError = th as NetResponseProcessor.ApiResponseErrorException
                assertThat(apiResponseError.errorCode, `is`("409"))
                System.out.println(JsonPrettyPrinter().print(apiResponseError))
            }

        }

    }
}