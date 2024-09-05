package com.example.restaurant_reviewer.model

import com.example.restaurant_reviewer.data.remote.response.RestaurantsItem

data class Restaurant(
    val restaurant: RestaurantsItem,
    val isFavorite: Boolean
)