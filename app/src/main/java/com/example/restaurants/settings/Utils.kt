package com.example.restaurants.settings

import java.math.RoundingMode
import java.text.DecimalFormat

object Utils {

    fun roundOffDecimal(number: Float): Float {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toFloat()
    }
}