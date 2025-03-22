package com.example.fetchhiring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchhiring.data.Hire
import com.example.fetchhiring.data.HireRepository
import com.example.fetchhiring.data.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HireViewModel(
    private val hiringRepository: HireRepository = HireRepository
): ViewModel() {
    private val _hiringListState = MutableStateFlow<UiState<List<HireGroups>>>(UiState.Loading())
    val hiringListState: StateFlow<UiState<List<HireGroups>>> = _hiringListState

    init {
        initFetchHireData()
    }

    private fun initFetchHireData() {
        viewModelScope.launch {
            _hiringListState.value = when (val response = hiringRepository.getHiringList()) {
                is NetworkResult.Success -> {
                    val hireList: List<Hire> = filterNamesAndSortByListId(response.data)
                    UiState.Success(groupByListId(hireList))
                }
                else -> {
                    UiState.Error()
                }
            }
        }
    }

    private fun groupByListId(hireList: List<Hire>): List<HireGroups> {
        val map = hireList
            .groupBy { it.listId }
            .map { mapOfNamesByListId ->
                val names = mapOfNamesByListId.value.map { it.name }.sortedBy { it }
                HireGroups(mapOfNamesByListId.key, names)
            }
        return map
    }

    private fun filterNamesAndSortByListId(list: List<Hire>): List<Hire> {
        return list.filter { hire ->
            !hire.name.isNullOrEmpty()
        }.sortedBy {
            it.listId
        }
    }
}

data class HireGroups(val listId: Int, val names: List<String?> = emptyList())

