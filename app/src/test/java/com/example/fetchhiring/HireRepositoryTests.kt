package com.example.fetchhiring

import com.example.fetchhiring.data.HireRepository
import com.example.fetchhiring.data.HireApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HireRepositoryTests {
    private val mockHireApiService = mockk<HireApiService>()
    @Before
    fun setUp() {
        HireRepository.inject(mockHireApiService)
    }

    @Test
    fun `when getHiringList() is called, data is returned`() = runTest {
        coEvery { mockHireApiService.getHireList() } returns emptyList()
        HireRepository.getHiringList()
    }
}