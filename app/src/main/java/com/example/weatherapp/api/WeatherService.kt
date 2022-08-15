package com.example.weatherapp.api

import com.example.weatherapp.screens.current.WeatherResponse
import com.example.weatherapp.screens.future.WeatherResponse7Day
import com.example.weatherapp.screens.future.WeatherThreeHour
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    // https://api.openweathermap.org/data/2.5/weather?q=HaNoi&appid=220dad74c7a8160adea535d772d08adb&units=metric

    @GET("data/2.5/weather")
     fun getCurrentWeatherData(
        @Query("q") city: String?,
        @Query("appid") apikey: String?,
        @Query("units") metric: String?

    ): Call<WeatherResponse>

    //https://api.openweathermap.org/data/2.5/forecast?q=$nameCity&appid=220dad74c7a8160adea535d772d08adb&units=metric
    @GET("data/2.5/forecast")
    fun getFutureWeatherThreeHour(
        @Query("q") city: String?,
        @Query("appid") apikey: String?,
        @Query("units") metric: String?

    ): Call<WeatherThreeHour>

    //https://api.weatherbit.io/v2.0/forecast/daily?city=HaNoi,&key=69ea3c8ee93e4a55b4a4ea81a456b157&days=1
    @GET("forecast/daily")
    fun getFutureWeatherData(
        @Query("city") city: String?,
        @Query("key") apikey: String?,
        @Query("days") day_number: Int?

    ): Call<WeatherResponse7Day>
}