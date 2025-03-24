package com.example.fetchhiring.data

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectivityInterceptor(private val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasNetworkConnectivity(context)) {
            throw ConnectivityException("Network connectivity issue")
        }
        return chain.proceed(chain.request())
    }

    private fun hasNetworkConnectivity(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities != null
    }
}

class ConnectivityException(message: String): IOException(message)