package com.example.restaurants.settings

enum class FilterBy(var value: Float) {
    ALL(0f),
    ABOVE_O_5_STARS(0.5f),
    ABOVE_1_STAR(1f),
    ABOVE_1_5_STARS(1.5f),
    ABOVE_2_STARS(2f),
    ABOVE_2_5_STARS(2.5f),
    ABOVE_3_STARS(3f),
    ABOVE_3_5_STARS(3.5f),
    ABOVE_4_STARS(4f),
    ABOVE_4_5_STARS(4.5f),
    ONLY_5_STARS(5f),

}