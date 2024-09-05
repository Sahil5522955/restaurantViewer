package com.example.restaurant_reviewer.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchRestaurantResponse(

	@field:SerializedName("founded")
	val founded: Int,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsItem>,

	@field:SerializedName("error")
	val error: Boolean
)