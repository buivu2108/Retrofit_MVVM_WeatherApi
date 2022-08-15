package com.example.weatherapp.screens.future

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import java.util.ArrayList

class FutureWeatherAdapter() : RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder>() {
    private val mListWeatherThreeHour: MutableList<WeatherThreeHour> = ArrayList<WeatherThreeHour>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imgStatusItem)
        private val tvTemp: TextView = itemView.findViewById(R.id.tvTempItem)
        private val tvDayOfWeek: TextView = itemView.findViewById(R.id.tvDayInWeekItem)



        fun fillData(weatherThreeHour: WeatherThreeHour){

        }
    }
    fun addData(weatherThreeHour: WeatherThreeHour) {
        mListWeatherThreeHour.add(weatherThreeHour)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather7Day = mListWeatherThreeHour[position]
        holder.fillData(weather7Day)
    }

    override fun getItemCount(): Int {// số item
        return mListWeatherThreeHour.size //7
    }

    fun setData(data: ArrayList<WeatherThreeHour>) {
        mListWeatherThreeHour.addAll(data)
        notifyDataSetChanged()
    }
}