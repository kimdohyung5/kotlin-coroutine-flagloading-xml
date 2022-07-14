package com.kimdo.flagloading.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimdo.flagloading.R
import com.kimdo.flagloading.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        viewModel = ViewModelProvider(this)[ ListViewModel::class.java]
        viewModel.refresh()


        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            countries?.let {
                binding.countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        }
        viewModel.countryLoadError.observe(this) { isError ->
            binding.listError.visibility = if(isError == "") View.GONE else View.VISIBLE
        }
        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        }
    }
}