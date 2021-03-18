package com.example.restaurants.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API error
 * */
@JsonClass(generateAdapter = true)
class ApiError(
    @Json(name = "status")
    var status: String,

    @Json(name = "message")
    var message: String
)
