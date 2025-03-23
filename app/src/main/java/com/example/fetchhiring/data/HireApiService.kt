package com.example.fetchhiring.data

import retrofit2.http.GET

interface HireApiService {
    @GET("/hiring.json")
    suspend fun getHireList(): List<Hire>
}