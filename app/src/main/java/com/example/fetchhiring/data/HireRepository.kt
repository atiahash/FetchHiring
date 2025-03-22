package com.example.fetchhiring.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object HireRepository {
    private lateinit var hireService: HireService
    fun init(hiringServiceClient: HireService) {
        hireService = hiringServiceClient
    }

    suspend fun getHiringList(): NetworkResult<List<Hire>> = withContext(Dispatchers.IO) {
        return@withContext try {
            NetworkResult.Success(hireService.getHireList())
        } catch (exception: Exception) {
            NetworkResult.Error(exception = exception)
        }
    }
}