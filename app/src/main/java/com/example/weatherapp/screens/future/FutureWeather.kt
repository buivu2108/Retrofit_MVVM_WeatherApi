package com.example.weatherapp.screens.future

import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.PurchaseFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityFutureWeatherBinding
import com.example.weatherapp.screens.Constants
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat


class FutureWeather : AppCompatActivity() {
    private lateinit var binding: ActivityFutureWeatherBinding
    private val futureWeatherViewModel: FutureWeatherViewModel by viewModels()
    private var mListWeatherThreeHour = ArrayList<ListWeatherThreeHour>()
    private lateinit var adapter:FutureWeatherAdapter
    private var position:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFutureWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        initEvent()


        val nameCity = intent.getStringExtra(Constants.keyPutExtra)

        if(nameCity != null){
            futureWeatherViewModel.getFutureData7Day(nameCity)
        }

        adapter = FutureWeatherAdapter(onItemClick = { listWeatherThreeHour, position ->
            changeView(listWeatherThreeHour,position,position+1)
        })

        binding.rcv7day.layoutManager = LinearLayoutManager(this@FutureWeather,RecyclerView.HORIZONTAL,false)
        binding.rcv7day.adapter = adapter

        binding.imgBtnBack.setOnClickListener {
            onBackPressed()
        }
        futureWeatherViewModel.data.observe(this) {
            if (it.list !=null){
                for (i in 0 until it.list.size) {
                    if (getWeather12HInDay(it.list[i].dtTxt!!) =="12"){
                        mListWeatherThreeHour.add(it.list[i])
                    }
                }
                adapter.setData(mListWeatherThreeHour)
            }
        }
        futureWeatherViewModel.actionShowCallApiFail.observe(this) {
            Toast.makeText(this@FutureWeather, Constants.textOnFailure, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initEvent() {
        binding.appBar.btnPurchase.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view_future, PurchaseFragment())
                .addToBackStack("purchase_fragment")
                .commit()
        }
        binding.imgNextDay.setOnClickListener {
            position += 1
            if(position > mListWeatherThreeHour.size-1){
                position =0
            }else {
                position
            }
            changeView(mListWeatherThreeHour,position-1,position)
            adapter.changeSelectedWeather(position)
        }
    }

    private fun initView() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (mListWeatherThreeHour.isNotEmpty()){
            changeView(mListWeatherThreeHour,0,1)
        }
    }

    private fun getWeather12HInDay(timer: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val inputDate = inputFormat.parse(timer)
        val outputFormat = SimpleDateFormat("HH")
        return outputFormat.format(inputDate)
    }

    private fun getDayToDateFormat(timer: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        val inputDate = inputFormat.parse(timer)
        val outputFormat = SimpleDateFormat("dd")
        return outputFormat.format(inputDate)
    }
    private fun getRankToDateFormat(timer: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val inputDate = inputFormat.parse(timer!!)
        val outputFormat = SimpleDateFormat("E")
        return outputFormat.format(inputDate!!)
    }
    private fun changeView(listWeatherThreeHour: ArrayList<ListWeatherThreeHour>,position:Int,positionNext: Int){
        var positionNumber = position
        var positionNextNumber = positionNext
        if (position ==-1){
            positionNumber = 0
            positionNextNumber = 1
        }
        val icon: String = listWeatherThreeHour[positionNumber].weather?.get(0)?.icon.toString()
        val imageUrl = "http://openweathermap.org/img/wn/$icon@2x.png"
        Picasso.get()
            .load(imageUrl)
            .into(binding.imgStatus)
        binding.tvTemp.text = "${listWeatherThreeHour[positionNumber].main?.temp}°C"
        binding.tvFeelLike.text = "Feels like ${listWeatherThreeHour[positionNumber].main?.feelsLike} °C"
        binding.tvStatusFuture.text = listWeatherThreeHour[positionNumber].weather?.get(0)?.description ?: ""
        binding.tvWind.text = "${listWeatherThreeHour[positionNumber].wind?.speed}m/s"
        binding.tvHumidity.text = "${listWeatherThreeHour[positionNumber].main?.humidity}%"
        binding.tvPressure.text = "${listWeatherThreeHour[positionNumber].main?.pressure} mb"

        val iconNext: String = listWeatherThreeHour[positionNextNumber].weather?.get(0)?.icon.toString()
        val imageUrlNext = "http://openweathermap.org/img/wn/$iconNext@2x.png"
        Picasso.get()
            .load(imageUrlNext)
            .into(binding.imgStatusFuture)

        binding.tvStatusNextDay.text = listWeatherThreeHour[positionNextNumber].weather?.get(0)?.description ?: ""
        binding.tvDayInWeek.text = "${getDayToDateFormat(listWeatherThreeHour[positionNextNumber].dtTxt.toString())},${getRankToDateFormat(listWeatherThreeHour[positionNextNumber].dtTxt.toString())}"
        binding.tvDayInWeek.text = "${listWeatherThreeHour[positionNextNumber].main?.tempMin} / ${listWeatherThreeHour[positionNextNumber].main?.tempMax}"
    }

}



