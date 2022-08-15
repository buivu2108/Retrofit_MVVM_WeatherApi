package com.example.weatherapp.screens.future


import com.google.gson.annotations.SerializedName

data class WeatherThreeHour(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List<ListWeatherThreeHour>?,
    @SerializedName("message")
    val message: Int?
)