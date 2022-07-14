package com.kimdo.flagloading.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimdo.flagloading.data.model.Country
import com.kimdo.flagloading.databinding.ItemCountryBinding
import com.kimdo.flagloading.other.loadImage

class CountryListAdapter( var countries: ArrayList<Country>) : RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.name.text = country.countryName
            binding.capital.text = country.capital
            binding.imageView.loadImage(country.flag)
        }
    }

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCountryBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind( countries[position] )
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}