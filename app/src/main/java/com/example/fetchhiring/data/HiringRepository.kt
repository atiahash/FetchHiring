package com.example.fetchhiring.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object HiringRepository {
    private val hiringServiceClient = HiringServiceClient.getInstance().create(FetchHiringService::class.java)

    suspend fun getHiringList() = withContext(Dispatchers.IO) {
        hiringServiceClient.getHireList()
    }

}