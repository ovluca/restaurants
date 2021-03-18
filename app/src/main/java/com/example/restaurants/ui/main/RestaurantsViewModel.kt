package com.example.restaurants.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.api.helper.NetworkResponse
import com.example.restaurants.model.Restaurant
import com.example.restaurants.repository.RestaurantsRepository
import com.example.restaurants.settings.FilterBy
import com.example.restaurants.settings.OrderBy
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RestaurantsViewModel : ViewModel() {
    private val repository = RestaurantsRepository()

    val adapterList: ArrayList<Restaurant> = ArrayList()
    var copyOfRestaurantsList: ArrayList<Restaurant> = ArrayList()
    val restaurantsLoadedData: MutableLiveData<Boolean> = MutableLiveData()
    internal val topThreeFrequentWords: HashMap<String, Int> = HashMap()

    init {
        viewModelScope.launch { getRestaurants() }
    }

    private suspend fun getRestaurants() {
        when (val response =
            repository.getRestaurants()) {
            is NetworkResponse.Success -> {
                adapterList.addAll(response.body.restaurants)
                adapterList.sortByDescending { restaurant -> restaurant.startRating }
                copyOfRestaurantsList.addAll(adapterList)
                getTop3FrequentWords()
                restaurantsLoadedData.postValue(true)
            }
            is NetworkResponse.ApiError -> {
                // TODO: 3/16/21
            }
            is NetworkResponse.NetworkError -> {
                // TODO: 3/16/21
            }
            is NetworkResponse.UnknownError -> {
                // TODO: 3/16/21
            }
        }
    }


    fun order(position: Int) {
        when (OrderBy.values()[position]) {
            OrderBy.Ascending -> {
                adapterList.sortBy { restaurant -> restaurant.startRating }
                copyOfRestaurantsList.sortBy { restaurant -> restaurant.startRating }
            }
            OrderBy.Descending -> {
                adapterList.sortByDescending { restaurant -> restaurant.startRating }
                copyOfRestaurantsList.sortByDescending { restaurant -> restaurant.startRating }
            }
        }
    }

    private fun filterRestaurantsArray(value: Float) {
        val filteredCopy = copyOfRestaurantsList.filter { it.startRating >= value }
        adapterList.clear()
        adapterList.addAll(filteredCopy)
    }

    fun filterByRating(position: Int) {
        when (val rating = FilterBy.values()[position]) {
            FilterBy.ALL -> {
                refreshAdapterList()
            }
            else -> filterRestaurantsArray(rating.value)
        }
    }

    private fun refreshAdapterList() {
        adapterList.clear()
        adapterList.addAll(copyOfRestaurantsList)
    }

    private fun getTop3FrequentWords(): HashMap<String, Int> {
        val allWordsFromDescriptionsArray: ArrayList<String> = ArrayList()
        copyOfRestaurantsList.forEach { restaurant ->
            run {
                allWordsFromDescriptionsArray.addAll(restaurant.description.toWords())
            }
        }

        val groups: HashMap<String, Int> =
            allWordsFromDescriptionsArray.groupingBy { it }.eachCount() as HashMap<String, Int>

        while (topThreeFrequentWords.size < 3) {

            val biggestValue = groups.maxByOrNull { it.value }

            if (biggestValue != null) {
                topThreeFrequentWords[biggestValue.key] = biggestValue.value
                groups.remove(biggestValue.key)
            }
        }

        return topThreeFrequentWords
    }


    private fun String.toWords(): List<String> {
        val regex = Regex("[^A-Za-z0-9 ]")
        val words = ArrayList<String>()
        for (w in this.trim(' ').split(" ")) {
            val wWithoutPunctuations = regex.replace(w, "").toLowerCase(Locale.ROOT)
            if (wWithoutPunctuations.isNotEmpty() && wWithoutPunctuations.length > 3) {
                words.add(wWithoutPunctuations)
            }
        }
        return words
    }
}

