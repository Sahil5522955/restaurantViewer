package com.example.restaurant_reviewer.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_reviewer.data.RestaurantRepository
import com.example.restaurant_reviewer.data.remote.response.RestaurantsItem
import com.example.restaurant_reviewer.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo : RestaurantRepository
) : ViewModel() {

    val listRestaurant: StateFlow<UiState<List<RestaurantsItem>>> get() = repo.listRestaurant

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getSearch(query: String) {
        _query.value = query
        viewModelScope.launch {
            repo.getSearchResponse(query)
        }
    }

}