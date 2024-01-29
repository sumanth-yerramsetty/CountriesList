package com.example.sampleproject.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleproject.network.RetrofitInstanceModule
import com.example.sampleproject.network.model.Country
import retrofit2.Response

class CountryRepository {
    private val countryService = RetrofitInstanceModule().theRetrofitInstance()
    private val countriesLiveData = MutableLiveData<List<Country>>()

    val countries: LiveData<List<Country>>
        get() = countriesLiveData

    suspend fun getCountriesFromAPI() {
        try {
            val response = countryService.getCountries()
            if (response.isSuccessful) {
                countriesLiveData.postValue(response.body())
            } else {
                when (response.code()) {
                    404 -> {
                        // Handle 404 Not Found error
                    }
                    500 -> {
                        // Handle 500 Internal Server Error
                    }
                    else -> {
                        // Handle other HTTP errors
                    }
                }
            }
        } catch (e: Exception) {
            // Handle exception here
        }
    }
}
