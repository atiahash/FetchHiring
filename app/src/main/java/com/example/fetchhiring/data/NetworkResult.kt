package com.example.fetchhiring.data

sealed class NetworkResult<T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error<T>(
        val message: String? = null,
        val exception: Throwable? = null
    ): NetworkResult<T>()
}