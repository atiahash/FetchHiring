package com.example.fetchhiring.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HireApiClient {
    private const val BASE_URL ="https://fetch-hiring.s3.amazonaws.com/"
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var gsonConverterFactory: GsonConverterFactory

    val hireApiService by lazy {

        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    fun inject(okHttpClient: OkHttpClient, gsonConverter: GsonConverterFactory) {
        this.okHttpClient = okHttpClient
        this.gsonConverterFactory = gsonConverter
    }
}