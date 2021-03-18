package com.example.restaurants.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.databinding.RowRestaurantBinding
import com.example.restaurants.model.Restaurant

/**
 * Adapter for rendering Exchange Rates
 * */
class RestaurantsAdapter(private val items: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {
        val binding =
            RowRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item: Restaurant = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}