package com.example.weatherapp.screens.future

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.Api
import com.example.weatherapp.base.SingleLiveEvent
import com.example.weatherapp.screens.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FutureWeatherViewModel:ViewModel() {

    val data = MutableLiveData<WeatherThreeHour>()
    val actionShowCallApiFail = SingleLiveEvent<Boolean>()

     fun getFutureData7Day(city:String) {
        val call = Api.retrofit.getFutureWeatherThreeHour(city,Constants.apiKey, Constants.metric)
        call.enqueue(object : Callback<WeatherThreeHour> {
            override fun onResponse(
                call: Call<WeatherThreeHour>,
                response: Response<WeatherThreeHour>
            ) {
              data.value = response.body()
            }
            override fun onFailure(call: Call<WeatherThreeHour>, t: Throwable) {
              actionShowCallApiFail.value= true
            }
        })
     }
}