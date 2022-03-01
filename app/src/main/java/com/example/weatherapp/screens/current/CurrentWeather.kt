package com.example.weatherapp.screens.current

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityCurrentWeatherBinding
import com.example.weatherapp.screens.future.FutureWeather
import com.example.weatherapp.screens.Constants
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class CurrentWeather : AppCompatActivity() {

    private lateinit var binding: ActivityCurrentWeatherBinding

    private val currentViewModel: CurrentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val city = binding.edtSearch.text.toString().trim()
            currentViewModel.getCurrentData(city)
            getData(city)
        }
        binding.btnNextDay.setOnClickListener {
            val city = binding.edtSearch.text.toString().trim()

            val intentFutureWeather = Intent(this@CurrentWeather, FutureWeather::class.java)
            intentFutureWeather.putExtra(Constants.keyPutExtra, city)
            startActivity(intentFutureWeather)
        }
        currentViewModel.data.observe(this) {
            val icon: String = it.weather[0].icon
            val imageUrl = "http://openweathermap.org/img/wn/$icon@2x.png"
            val day: String = it.dt.toString()
            val l: Long = day.toLong()
            val date = Date(l * 1000L)
            val simpleDateFormat = SimpleDateFormat(Constants.simpleDateValue)
            val Date: String = simpleDateFormat.format(date)

            Picasso.get()
                .load(imageUrl)
                .into(binding.imgStatus)

            if (it != null) {
                binding.tvNameCity.text = it.name
                binding.tvNameAddress.text = it.sys.country
                binding.tvTemp.text = it.main.temp.toString() + "Độ C"
                binding.tvStatus.text = it.weather[0].description
                binding.tvHumidity.text = it.main.humidity.toString() + "%"
                binding.tvCloud.text = it.clouds.all.toString() + "%"
                binding.tvWind.text = it.wind.speed.toString() + "m/s"
                binding.tvDateUpdate.text = Date
            }
        }
        currentViewModel.actionShowCallApiFail.observe(this){
            Toast.makeText(this@CurrentWeather, Constants.textOnFailure, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(city:String) {
        Toast.makeText(this,city,Toast.LENGTH_LONG).show()
    }
}