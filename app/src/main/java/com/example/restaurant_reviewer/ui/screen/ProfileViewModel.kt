package com.example.restaurant_reviewer.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant_reviewer.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val repo: RestaurantRepository
) : ViewModel() {

    fun deleteAllFavs() {
        viewModelScope.launch {
            repo.deleteAllFavorites()
        }
    }
}