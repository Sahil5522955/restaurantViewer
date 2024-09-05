package com.example.restaurant_reviewer.di
import android.content.Context
import com.example.restaurant_reviewer.data.RestaurantRepository
import com.example.restaurant_reviewer.data.local.RestaurantDatabase
import com.example.restaurant_reviewer.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): RestaurantRepository {
        val apiService = ApiConfig.getApiService()
        val database = RestaurantDatabase.getDatabase(context)
        val dao = database.restaurantDao()
        return RestaurantRepository.getInstance(apiService, dao)
    }
}