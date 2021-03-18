package com.example.restaurants.api

import com.example.restaurants.api.helper.NetworkResponse
import com.example.restaurants.model.ApiResponse
import com.example.restaurants.model.ApiError
import com.example.restaurants.settings.Constants.PATH_RESTAURANTS
import retrofit2.http.GET

/**
 * API Requests
 * */
interface ApiService {

    @GET(PATH_RESTAURANTS)
    suspend fun getRestaurants(): NetworkResponse<ApiResponse, ApiError>

}