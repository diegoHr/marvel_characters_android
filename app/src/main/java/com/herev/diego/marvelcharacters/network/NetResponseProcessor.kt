package com.herev.diego.marvelcharacters.network

import com.google.gson.Gson
import retrofit2.Response

class NetResponseProcessor {
    private val gson = Gson()

    @Throws(ApiResponseErrorException::class, NetErrorException::class)
    @Suppress("UNCHECKED_CAST")
    fun <T : ApiResponse> checkResponse  (response: Response<T>) : T {
        val bitResponse = response.body()

        if(bitResponse == null || bitResponse.code != 200){
            val errorResponse = if(response.errorBody() != null)gson.fromJson(response.errorBody()?.string(), ApiResponseError::class.java) else null
            if(errorResponse != null){
                throwBitResponseError(errorResponse, response)
            }
            throw NetErrorException(response as Response<Any>)
        }

        return bitResponse

    }
    @Throws(ApiResponseErrorException::class)
    private fun <T : ApiResponse> throwBitResponseError (errorResponse : ApiResponseError, response: Response<T>){

        val responseRaw : okhttp3.Response = response.raw()

        throw ApiResponseErrorException(errorResponse.status ?: "", errorResponse.code ?: "", responseRaw.request().url().toString())
    }

    inner class NetErrorException (response: Response<Any>) : Exception ("(${(response.raw() as okhttp3.Response).request().url()})Net error (${response.code()}) -> ${response.message()}\n${gson.toJson(response.errorBody())}")
    class ApiResponseErrorException (message : String, val errorCode : String, val endpoint : String) : Exception ("($endpoint)$errorCode:$message")


}