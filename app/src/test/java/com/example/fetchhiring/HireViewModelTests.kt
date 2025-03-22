package com.example.fetchhiring

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fetchhiring.data.Hire
import com.example.fetchhiring.data.HireRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HireViewModelTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockHireRepository = mockk<HireRepository>(relaxed = true)
    private lateinit var hireViewModel: HireViewModel

    @Before
    fun setUp () {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `when viewModel is instantiated, fetch of data is called`() = runTest {
        hireViewModel = HireViewModel(mockHireRepository)
        coEvery { mockHireRepository.getHiringList() } returns emptyList()
        advanceUntilIdle()
        coVerify(exactly = 1) { mockHireRepository.getHiringList() }
    }

    @Test
    fun `when viewModel is instantiated, hire list is fetched, filtered, and sorted `() = runTest {
        hireViewModel = HireViewModel(mockHireRepository)
        coEvery { mockHireRepository.getHiringList() } returns getMockHireList()
        advanceUntilIdle()
        Assert.assertEquals(getFilteredData(), hireViewModel.hiringListState.value)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    private fun getMockHireList(): List<Hire> {
        return listOf(
            Hire(id = 5, listId = 3, name = "Item 22"),
            Hire(id = 7, listId = 5, name = "123"),
            Hire(id = 6, listId = 4, name = ""),
            Hire(id = 9, listId = 6, name = "25"),
            Hire(id = 2, listId = 1, name = null),
            Hire(id = 11, listId = 7, name = "Item 300"),
            Hire(id = 10, listId = 5, name = null),
            Hire(id = 8, listId = 6, name = ""),
            Hire(id = 4, listId = 2, name = "Item 24"),
            Hire(id = 1, listId = 1, name = "Item 23"),
            Hire(id = 3, listId = 2, name = "Item 21"),
            Hire(id = 12, listId = 7, name = "Item 200"),
        )
    }

    private fun getFilteredData(): List<HireGroups> {
        return listOf(
            HireGroups(listId = 1, listOf("Item 23")),
            HireGroups(listId = 2, listOf("Item 21", "Item 24")),
            HireGroups(listId = 3, listOf("Item 22")),
            HireGroups(listId = 5, listOf("123")),
            HireGroups(listId = 6, listOf("25")),
            HireGroups(listId = 7, listOf("Item 200", "Item 300")),

        )
    }
}