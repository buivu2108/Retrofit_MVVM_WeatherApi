package com.example.weatherapp.screens.current

import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.Api
import com.example.weatherapp.screens.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.base.SingleLiveEvent


class CurrentViewModel : ViewModel() {

    val data = MutableLiveData<WeatherResponse>()
    val actionShowCallApiFail = SingleLiveEvent<Unit>()// tránh trường hợp lỗi chết view còn giá trị view model
    //mở nên sẽ thấy luôn sự kiện fail

    fun getCurrentData(city: String) {
        val call = Api.retrofit.getCurrentWeatherData(city, Constants.apiKey, Constants.metric)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>, response: Response<WeatherResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                actionShowCallApiFail.call()
            }
        })
    }
}