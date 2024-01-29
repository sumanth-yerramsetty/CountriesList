package com.example.sampleproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleproject.databinding.ActivityMainBinding
import com.example.sampleproject.ui.adapters.CountriesRecyclerAdapter
import com.example.sampleproject.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var countryAdapter: CountriesRecyclerAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setUpViewModel()
        setUpRecyclerView()

        mainViewModel.countries.observe(this, Observer { countries ->
            if (countries != null) {
                countryAdapter.submitCountriesList(countries)
            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.isShowProgress.observe(this, Observer {
            activityMainBinding.mainProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mainViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        if (savedInstanceState == null) {
            mainViewModel.getCountriesFromAPI()
        }
    }

    private fun setUpRecyclerView() = activityMainBinding.countryRecyclerView.apply {
        countryAdapter = CountriesRecyclerAdapter()
        adapter = countryAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}
