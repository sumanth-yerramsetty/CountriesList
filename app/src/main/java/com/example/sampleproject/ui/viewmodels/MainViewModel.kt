package com.example.sampleproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.data.repository.CountryRepository
import com.example.sampleproject.network.model.Country
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val countryRepository = CountryRepository()

    val countries: LiveData<List<Country>> = countryRepository.countries
    val errorMessage = MutableLiveData<String>()
    val isShowProgress = MutableLiveData<Boolean>()

    fun getCountriesFromAPI() {
        isShowProgress.value = true
        viewModelScope.launch {
            try {
                countryRepository.getCountriesFromAPI()
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Unknown error occurred"
            } finally {
                isShowProgress.value = false
            }
        }
    }
}
