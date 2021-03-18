package com.example.restaurants.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.databinding.RowRestaurantBinding
import com.example.restaurants.model.Restaurant
import com.squareup.picasso.Picasso

/**
 * ExchangeRates View Holder
 * */
class RestaurantViewHolder(private val binding: RowRestaurantBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(restaurant: Restaurant) {
        Picasso.get().load(restaurant.image).into(binding.restaurantImage);
        binding.restaurantDescription.text = restaurant.description
        binding.restaurantName.text = restaurant.name
        binding.restaurantRating.text = restaurant.startRating.toString()
    }

}
