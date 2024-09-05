package com.example.restaurant_reviewer.ui

import androidx.lifecycle.ViewModel
import com.example.restaurant_reviewer.data.RestaurantRepository
import androidx.lifecycle.ViewModelProvider
import com.example.restaurant_reviewer.ui.screen.detail.DetailViewModel
import com.example.restaurant_reviewer.ui.screen.favorite.FavoriteViewModel
import com.example.restaurant_reviewer.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repo : RestaurantRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}