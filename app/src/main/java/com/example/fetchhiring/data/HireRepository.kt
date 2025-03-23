package com.example.fetchhiring.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object HireRepository {
    private lateinit var hireApiService: HireApiService
    fun inject(hiringServiceClient: HireApiService) {
        hireApiService = hiringServiceClient
    }

    suspend fun getHiringList(): NetworkResult<List<Hire>> = withContext(Dispatchers.IO) {
        return@withContext try {
            NetworkResult.Success(hireApiService.getHireList())
        } catch (exception: Exception) {
            NetworkResult.Error(exception = exception)
        }
    }
}