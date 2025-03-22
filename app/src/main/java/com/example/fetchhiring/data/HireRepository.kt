package com.example.fetchhiring.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object HireRepository {
    private lateinit var hireService: HireService
    fun init(hiringServiceClient: HireService) {
        hireService = hiringServiceClient
    }

    suspend fun getHiringList() = withContext(Dispatchers.IO) {
        hireService.getHireList()
    }
}