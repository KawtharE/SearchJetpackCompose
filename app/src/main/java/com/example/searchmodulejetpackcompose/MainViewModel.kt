package com.example.searchmodulejetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchmodulejetpackcompose.Student.Companion.provideStudentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(FlowPreview::class)
class MainViewModel : ViewModel() {

    private val _searchInput = MutableStateFlow("")
    val searchInput = _searchInput.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _students = MutableStateFlow(provideStudentList())
    val students = searchInput
        .debounce(1000L) // To improve search when implementing search with real remote API: it create a delay until next block is executed, meanwhile if the search input change value the previous call/emission is canceled and the new value will be used for search, this way we reduce the API call
        .onEach { _isSearching.update { true } }
        .combine(_students) { input, students ->
            if (input.isBlank() || input.isEmpty()) {
                students
            } else {
                delay(2000) // just to perform Network delay and be able to see the progressbar
                students.filter {
                    it.interpretInputText(input)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _students.value
        )

    fun onSearchInput(input: String) {
        _searchInput.value = input
    }
}