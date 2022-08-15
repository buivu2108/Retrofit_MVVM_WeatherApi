package com.example.weatherapp.screens.future

import android.graphics.Color.blue
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherapp.databinding.ActivityFutureWeatherBinding
import com.example.weatherapp.screens.Constants
import java.text.DateFormat
import java.text.SimpleDateFormat


class FutureWeather : AppCompatActivity() {
    private lateinit var binding: ActivityFutureWeatherBinding
    private val futureWeatherViewModel: FutureWeatherViewModel by viewModels()

    private val adapter = FutureWeatherAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFutureWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val nameCity = intent.getStringExtra(Constants.keyPutExtra)

        if(nameCity != null){
            futureWeatherViewModel.getFutureData7Day(nameCity)
        }

        binding.rcv7day.layoutManager = LinearLayoutManager(this@FutureWeather,RecyclerView.HORIZONTAL,false)
        binding.rcv7day.adapter = adapter

        binding.imgBtnBack.setOnClickListener {
            onBackPressed()
        }
        futureWeatherViewModel.data.observe(this) {

        }
        futureWeatherViewModel.actionShowCallApiFail.observe(this) {
            Toast.makeText(this@FutureWeather, Constants.textOnFailure, Toast.LENGTH_SHORT).show()
        }

       fun getHourToDateFormat(timer:String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val inputDate = inputFormat.parse(timer)
            val outputFormat = SimpleDateFormat("HH:mm")
            val outputTimer = outputFormat.format(inputDate)
            return outputTimer
        }

        fun setBackGroundColor(timer: String) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            val inputDate = inputFormat.parse(timer);
            val outputFormat = SimpleDateFormat("HH")
            val outputTimer = outputFormat.format(inputDate)
            if (outputTimer != "03" && outputTimer != "00" && outputTimer != "21") {
                return
            } else {
                return
            }

        }

        fun getDayToDateFormat(timer: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            val inputDate = inputFormat.parse(timer)
            val outputFormat = SimpleDateFormat("dd")
            val day = outputFormat.format(inputDate)
            return day
        }

       fun getRankToDateFormat(timer: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val inputDate = inputFormat.parse(timer)
            val outputFormat = SimpleDateFormat("E")
            val rank = outputFormat.format(inputDate)
            return rank
        }

        fun getWeather12HInDay(timer: String): String {
            val inputFormat =SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val inputDate = inputFormat.parse(timer)
            val outputFormat = SimpleDateFormat("HH")
            val rank = outputFormat.format(inputDate)
            return rank
        }

       fun getListWeather12h(mListWeather:List<ListWeatherThreeHour>) {
            val mList = ArrayList<ListWeatherThreeHour>()

        }

    }
}



