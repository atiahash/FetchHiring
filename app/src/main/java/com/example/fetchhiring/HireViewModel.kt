package com.example.fetchhiring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchhiring.data.HireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HireViewModel(private val hiringRepository: HireRepository = HireRepository): ViewModel() {
    private val _hiringListState = MutableStateFlow<List<HireGroups>>(emptyList())
    val hiringListState: StateFlow<List<HireGroups>> = _hiringListState

    init {
        fetchHiringData()
    }

    private fun fetchHiringData() {
        viewModelScope.launch {
            val hireList = hiringRepository.getHiringList()
                .filter { hire ->
                    !hire.name.isNullOrEmpty()
                }
                .sortedBy {
                    it.listId
                }

            _hiringListState.value = hireList
                .groupBy { it.listId }
                .map { mapOfNamesByListId ->
                    val names = mapOfNamesByListId.value.map { it.name }.sortedBy { it }
                    HireGroups(mapOfNamesByListId.key, names)
                }
        }
    }
}

data class HireGroups(val listId: Int, val names: List<String?> = emptyList())

