package com.example.fetchhiring

import com.example.fetchhiring.data.HireRepository
import com.example.fetchhiring.data.HireService
import com.example.fetchhiring.data.HireServiceClient

class HireContainer {
    private val hiringServiceClient: HireService = HireServiceClient.getInstance().create(HireService::class.java)

    val hireRepository = HireRepository

    init {
        hireRepository.init(hiringServiceClient)
    }
}