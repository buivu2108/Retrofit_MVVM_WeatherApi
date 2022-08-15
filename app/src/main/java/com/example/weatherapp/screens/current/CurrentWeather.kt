package com.example.weatherapp.screens.current

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityCurrentWeatherBinding
import com.example.weatherapp.screens.Constants
import com.example.weatherapp.screens.future.FutureWeather
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
        initViewModels()
        initEvent()


    }
    private fun initEvent() {
        binding.btnNext5Day.setOnClickListener {
            val city = binding.edtSearch.text.toString().trim()
            if (city.isNotEmpty()){
                val intentFutureWeather = Intent(this@CurrentWeather, FutureWeather::class.java)
                intentFutureWeather.putExtra(Constants.keyPutExtra, city)
                startActivity(intentFutureWeather)
            }else{
                val intentFutureWeather = Intent(this@CurrentWeather, FutureWeather::class.java)
                intentFutureWeather.putExtra(Constants.keyPutExtra, "hanoi")
                startActivity(intentFutureWeather)
            }
        }

        binding.edtSearch.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val city = binding.edtSearch.text.toString().trim()
                currentViewModel.getCurrentData(city)
                getData(city)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun initViewModels() {
        currentViewModel.getCurrentData("hanoi")
        currentViewModel.actionShowCallApiFail.observe(this){
            Toast.makeText(this@CurrentWeather, Constants.textOnFailure, Toast.LENGTH_SHORT).show()
        }

        currentViewModel.data.observe(this) {
            val icon: String = it.weather[0].icon
            val imageUrl = "http://openweathermap.org/img/wn/$icon@2x.png"
            val day: String = it.dt.toString()
            val l: Long = day.toLong()
            val date = Date(l * 1000L)

            Picasso.get()
                .load(imageUrl)
                .into(binding.imgStatus)

            if (it != null) {
                binding.tvNameCity.text = it.name
                binding.tvTemp.text = "${it.main.temp}°C"
                binding.tvStatus.text = it.weather[0].description
                binding.tvHumidityTop.text = "${it.main.humidity}%"
                binding.tvWind.text = "${it.wind.speed}m/s"
                binding.tvTempMax.text = "Max Temp: ${it.main.temp_max}°C"
                binding.tvTempMin.text = "Min Temp: ${it.main.temp_min}°C"
                binding.tvSunrise.text = getHourToString(it.sys.sunrise.toString())
                binding.tvSunset.text = getHourToString(it.sys.sunset.toString())
                binding.tvPressureTop.text = "${it.main.pressure} mb"

            }
        }
    }

    private fun getHourToString(time: String): String {
        val l: Long = time.toLong()
        val timer = Date(l * 1000L)
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        return simpleDateFormat.format(timer)
    }

    private fun getData(city:String) {
        binding.tvNameCity.text = city
        Toast.makeText(this,city,Toast.LENGTH_LONG).show()
    }
}