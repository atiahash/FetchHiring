package com.example.fetchhiring.data

import retrofit2.http.GET

interface FetchHiringService {
    @GET("/hiring.json")
    suspend fun getHireList(): List<Hire>
}