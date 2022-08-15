package com.example.weatherapp.screens.future

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.weatherapp.databinding.ActivityFutureWeatherBinding
import com.example.weatherapp.screens.Constants


class FutureWeather : AppCompatActivity() {
    private lateinit var binding: ActivityFutureWeatherBinding
    private val futureWeatherViewModel: FutureWeatherViewModel by viewModels()

    private val adapter = TestAdapter()

    private var age: Int? = 3

    /*fun test(age: Int){

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        //let, apply, run
        /*age?.let {
            test(it)
        }*/


        super.onCreate(savedInstanceState)
        binding = ActivityFutureWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val nameCity = intent.getStringExtra(Constants.keyPutExtra)//k nhận đc nameCity

        if(nameCity != null){
            futureWeatherViewModel.getFutureData7Day(nameCity)// gọi đến function getFutureData7Day bên view model
        }

        binding.tvNameCity.text = nameCity



        binding.rcv7day.layoutManager = LinearLayoutManager(this@FutureWeather)
        binding.rcv7day.adapter = adapter

        binding.imgBtnBack.setOnClickListener {
            onBackPressed()
        }
        futureWeatherViewModel.data.observe(this) {
            /*//doc lai hieu ky
            adapter.setData(it.data)
            *//*for (i in 0 until it.data.size) {
                val date: String = it.data!![i].valid_date
                val weather: Weather = it.data[i].weather
                val minTemp: String = it.data[i].min_temp.toString()
                val maxTemp: String = it.data[i].max_temp.toString()
                adapter.addData(Weather7Day(date, weather, maxTemp.toDouble(), minTemp.toDouble()))
            }*/
        }
        futureWeatherViewModel.actionShowCallApiFail.observe(this) {
            Toast.makeText(this@FutureWeather, Constants.textOnFailure, Toast.LENGTH_SHORT).show()
        }
    }
}



