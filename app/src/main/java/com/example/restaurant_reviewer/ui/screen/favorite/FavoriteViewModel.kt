package com.example.restaurant_reviewer.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_reviewer.data.RestaurantRepository
import com.example.restaurant_reviewer.data.local.RestaurantEntity
import com.example.restaurant_reviewer.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repo : RestaurantRepository
) : ViewModel() {

    private val _listFavorite: MutableStateFlow<UiState<List<RestaurantEntity>>> = MutableStateFlow(UiState.Loading)
    val listFavorite: StateFlow<UiState<List<RestaurantEntity>>> get() = _listFavorite

    init {
        getAllFavoriteResto()
    }
    private fun getAllFavoriteResto(){
        viewModelScope.launch {
            _listFavorite.value = UiState.Loading
            repo.getAllFavoriteResto()
                .collect { resto ->
                    _listFavorite.value = UiState.Success(resto)
                }
        }
    }

    fun isFavorite(id: String): Flow<Boolean> = listFavorite.map { uiState ->
        when (uiState) {
            is UiState.Success -> {
                val favoriteList = uiState.data
                favoriteList.any { restaurantEntity ->
                    restaurantEntity.id == id
                }
            }
            else -> false
        }
    }

    fun saveFavoriteResto(resto: RestaurantEntity) {
        viewModelScope.launch {
            repo.saveFavorite(resto)
        }
    }

    fun deleteFavoriteResto(id: String) {
        viewModelScope.launch {
            repo.deleteFavorite(id)
        }
    }
}