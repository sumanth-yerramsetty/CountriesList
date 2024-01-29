package com.example.sampleproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.databinding.RawCountriesItemLayoutBinding
import com.example.sampleproject.network.model.Country
import com.example.sampleproject.utils.Const.Companion.COMMA_SEPARATOR

class CountriesRecyclerAdapter : RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {
    private var countries: List<Country> = emptyList()

    inner class CountriesViewHolder(private val rawCountriesItemLayoutBinding: RawCountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(rawCountriesItemLayoutBinding.root) {

        fun bind(country: Country) {
            rawCountriesItemLayoutBinding.apply {
                nameOfCountry.text = country.name + COMMA_SEPARATOR
                regionOfCountry.text = country.region
                codeOfCountry.text = country.code
                capitalOfCountry.text = country.capital
            }
        }
    }

    fun submitCountriesList(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RawCountriesItemLayoutBinding.inflate(inflater, parent, false)
        return CountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size
}
