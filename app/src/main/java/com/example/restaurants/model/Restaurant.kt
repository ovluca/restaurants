package com.example.restaurants.model

import com.example.restaurants.settings.Utils.roundOffDecimal
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Restaurant(
    var description: String,
    var name: String,
    var image: String,
    var numRatings: Float,
    var totalStars: Float
) {

    @Transient
    val startRating: Float = if (numRatings > 0) {
        roundOffDecimal(totalStars.div(numRatings))
    } else numRatings

}