package com.example.restaurant_reviewer.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_reviewer.data.RestaurantRepository
import com.example.restaurant_reviewer.data.remote.response.DetailRestaurantResponse
import com.example.restaurant_reviewer.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo : RestaurantRepository,
) : ViewModel() {
    val detailRestaurant: StateFlow<UiState<DetailRestaurantResponse>> = repo.detailRestaurant

    fun getDetailRestaurant(id: String) {
        viewModelScope.launch{
            repo.getDetailRestaurant(id)
        }
    }
}