package com.example.weatherapp.screens.future

import com.example.weatherapp.screens.current.Weather

data class WeatherResponse7Day(val data: ArrayList<Weather7Day>)
data class Weather7Day(
    val valid_date: String,
    val weather: Weather,
    val max_temp: Double,
    val min_temp: Double
)


