package com.example.weatherapp.api

import com.example.weatherapp.screens.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    
    private val gson = GsonBuilder()
        .setDateFormat(Constants.simpleDateValue)
        .create()
    val retrofit: WeatherService = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(WeatherService::class.java)

}