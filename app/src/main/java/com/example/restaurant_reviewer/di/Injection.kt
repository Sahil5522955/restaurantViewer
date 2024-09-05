package com.example.restaurant_reviewer.di
import android.content.Context
import com.example.restaurant_reviewer.data.RestaurantRepository
import com.example.restaurant_reviewer.data.local.RestaurantDatabase
import com.example.restaurant_reviewer.data.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object Injection {

    private var BASE_URL = "https://restaurant-api.dicoding.dev/"

    @Provides
    fun provideRepository(apiService: ApiService,@ApplicationContext context: Context): RestaurantRepository {
        val database = RestaurantDatabase.getDatabase(context)
        val dao = database.restaurantDao()
        return RestaurantRepository.getInstance(apiService, dao)
    }


    @Provides
    fun getApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}