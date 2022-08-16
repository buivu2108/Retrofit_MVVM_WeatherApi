package com.example.weatherapp.screens.future

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.ArrayList

class FutureWeatherAdapter( private var onItemClick: (listWeatherHour:ArrayList<ListWeatherThreeHour>,position: Int) -> Unit) : RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder>() {
    private val mListWeatherThreeHour= ArrayList<ListWeatherThreeHour>()
    private var selectedWeather: Int = 0
    private lateinit var context: Context

    fun setData(data: ArrayList<ListWeatherThreeHour>) {
        mListWeatherThreeHour.clear()
        mListWeatherThreeHour.addAll(data)
        notifyDataSetChanged()
    }
    
    fun changeSelectedWeather(weatherSelected: Int) {
        selectedWeather = weatherSelected
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgStatus: ImageView = itemView.findViewById(R.id.imgStatusItem)
        private val imgBg: ImageView = itemView.findViewById(R.id.bgItem)
        private val tvTemp: TextView = itemView.findViewById(R.id.tvTempItem)
        private val tvDayOfWeek: TextView = itemView.findViewById(R.id.tvDayInWeekItem)

        fun fillData(weatherThreeHour: ArrayList<ListWeatherThreeHour>, position: Int) {
            val icon: String = weatherThreeHour[position].weather?.get(0)?.icon.toString()
            val imageUrl = "http://openweathermap.org/img/wn/$icon@2x.png"
            Picasso.get()
                .load(imageUrl)
                .into(imgStatus)
            tvTemp.text = "${weatherThreeHour[position].main!!.temp}°C"
            tvDayOfWeek.text = getRankToDateFormat(weatherThreeHour[position].dtTxt)
            imgBg.setOnClickListener {
                onItemClick.invoke(weatherThreeHour,position)
                changeSelectedWeather(position)
            }
            if (selectedWeather == position) {
                imgBg.setColorFilter(
                    ContextCompat.getColor(context, R.color.selecter),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                tvTemp.setTextColor(R.color.white)
                tvDayOfWeek.setTextColor(R.color.white)
            }else{
                imgBg.setColorFilter(
                    ContextCompat.getColor(context, R.color.white),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                tvTemp.setTextColor(R.color.teal_200)
                tvDayOfWeek.setTextColor(R.color.teal_200)
            }
        }

        private fun getRankToDateFormat(timer: String?): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val inputDate = inputFormat.parse(timer!!)
            val outputFormat = SimpleDateFormat("E")
            return outputFormat.format(inputDate!!)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mListWeatherThreeHour = mListWeatherThreeHour
        holder.fillData(mListWeatherThreeHour, position)
    }

    override fun getItemCount(): Int {
        return mListWeatherThreeHour.size
    }


}