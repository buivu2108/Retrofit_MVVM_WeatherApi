package com.example.weatherapp.api

import com.example.weatherapp.screens.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API7Day {
    private val gson = GsonBuilder()
        .setDateFormat(Constants.simpleDateValue)
        .create()
    val retrofit: WeatherService = Retrofit.Builder()
        .baseUrl("https://api.weatherbit.io/v2.0/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(WeatherService::class.java)
}