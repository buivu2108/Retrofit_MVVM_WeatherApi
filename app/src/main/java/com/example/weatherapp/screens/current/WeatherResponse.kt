package com.example.weatherapp.screens.current

data class WeatherResponse(
    val weather: ArrayList<Weather>,
    val dt: Int, val name: String, val sys: Address,
    val clouds: Cloud, val wind: Wind, val main: Main
)

data class Weather(
    val description: String,
    val icon: String
)

data class Address(val country: String,val sunrise:Long,
    val sunset:Long)
data class Wind(val speed: Double)
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Cloud(val all: Double)




