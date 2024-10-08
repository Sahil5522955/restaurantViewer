package com.example.restaurant_reviewer.data.remote.retrofit

import com.example.restaurant_reviewer.data.remote.response.DetailRestaurantResponse
import com.example.restaurant_reviewer.data.remote.response.SearchRestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("detail/{id}")
    suspend fun getDetailRestaurant(
        @Path("id") id: String,
    ): DetailRestaurantResponse

    @GET("search")
    suspend fun getSearchRestaurant(@Query("q") query: String) : SearchRestaurantResponse
}