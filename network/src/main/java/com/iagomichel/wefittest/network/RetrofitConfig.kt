package com.iagomichel.wefittest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    private const val TIMEOUT = 60L
    private const val TIMEOUT_DEBUG = 120L

    fun <T> create(
        isDebugMode: Boolean,
        service: Class<T>,
        baseUrl: String
    ): T =
        buildApi(
            url = baseUrl,
            okHttpClient = getFullHttpClient(isDebugMode).build()
        ).create(service)

    private fun getFullHttpClient(
        isDebugMode: Boolean
    ): OkHttpClient.Builder {
        val client =
            getHttpClient(isDebugMode)
            .addInterceptor(getHttpLogging(isDebugMode))

        return client
    }

    private fun getHttpClient(
        isDebugMode: Boolean,
    ): OkHttpClient.Builder {
        val timeout =
            if (isDebugMode)
                TIMEOUT_DEBUG
            else
                TIMEOUT

        return OkHttpClient
            .Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
    }

    private fun buildApi(url: String, okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    private fun getHttpLogging(
        isDebugMode: Boolean
    ): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (isDebugMode)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )
}
