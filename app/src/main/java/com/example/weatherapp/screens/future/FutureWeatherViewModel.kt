package com.example.weatherapp.screens.future

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.API7Day
import com.example.weatherapp.base.SingleLiveEvent
import com.example.weatherapp.screens.Constants
import com.example.weatherapp.screens.model.WeatherResponse7Day
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FutureWeatherViewModel:ViewModel() {

    val data = MutableLiveData<WeatherResponse7Day>()
    val actionShowCallApiFail = SingleLiveEvent<Boolean>()// tránh trường hợp lỗi chết view còn giá trị view model
    //mở nên sẽ thấy luôn sự kiện fail
     fun getFutureData7Day(city:String) {
        val call = API7Day.retrofit.getFutureWeatherData(city, Constants.apiKey7Day, 7)
        call.enqueue(object : Callback<WeatherResponse7Day> {
            override fun onResponse(
                call: Call<WeatherResponse7Day>,
                response: Response<WeatherResponse7Day>
            ) {
              data.value = response.body()
            }
            override fun onFailure(call: Call<WeatherResponse7Day>, t: Throwable) {
              actionShowCallApiFail.value= true
            }
        })
     }
}