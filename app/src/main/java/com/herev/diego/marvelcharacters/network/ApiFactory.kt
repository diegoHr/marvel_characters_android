package com.herev.diego.marvelcharacters.network

import com.google.gson.GsonBuilder
import com.herev.diego.marvelcharacters.BuildConfig
import com.herev.diego.marvelcharacters.utils.Md5Utils
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class ApiFactory {

    private val md5Utils = Md5Utils()

    private val dateMask =  "yyyy-MM-dd'T'HH:mm:ss-SSSS" //"2014-04-29T14:18:17-0400"

    private val gson = GsonBuilder().setDateFormat(dateMask).create()

    fun createMarvelApiService() : MarvelApiService {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)

        val timeStamp = Date().time.toString()

        builder.addInterceptor {
            var request: Request = it.request()
            val url: HttpUrl = request.url().newBuilder()
                .addQueryParameter("ts", timeStamp)
                .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                .addQueryParameter("hash", md5Utils.genHash(constructApiPasswd(timeStamp)))
                .build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(builder.build())
            .build()

        return retrofit.create(MarvelApiService::class.java)
    }

    private fun constructApiPasswd (timeStamp : String ) : String {
        return "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
    }
}