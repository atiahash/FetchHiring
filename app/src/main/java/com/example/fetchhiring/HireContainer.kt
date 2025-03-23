package com.example.fetchhiring

import android.content.Context
import com.example.fetchhiring.data.HireRepository
import com.example.fetchhiring.data.HireApiService
import com.example.fetchhiring.data.HireApiClient
import com.example.fetchhiring.data.NetworkConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class HireContainer(context: Context) {

    init {
        initializeHireApiClient(context)

        val hiringServiceClient = HireApiClient.hireApiService.create(HireApiService::class.java)
        HireRepository.inject(hiringServiceClient)
    }

    private fun initializeHireApiClient(context: Context) {
        val networkInterceptor = NetworkConnectivityInterceptor(context)
        val httpLoggingInterceptor = if (!isProdBuild()) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val okHttpClient: OkHttpClient.Builder =
            OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(networkInterceptor)

        HireApiClient.inject(
            okHttpClient = okHttpClient.build(),
            gsonConverter = GsonConverterFactory.create()
        )
    }

    private fun isProdBuild(): Boolean {
        //TODO: get from BuildConfig
        return false
    }
}