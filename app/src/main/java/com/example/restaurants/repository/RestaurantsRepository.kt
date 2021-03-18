package com.example.restaurants.repository

import com.example.restaurants.api.helper.NetworkResponse
import com.example.restaurants.api.webservice
import com.example.restaurants.model.ApiResponse
import com.example.restaurants.model.ApiError


class RestaurantsRepository {

    suspend fun getRestaurants(): NetworkResponse<ApiResponse, ApiError> {
        return webservice.getRestaurants()
    }
}