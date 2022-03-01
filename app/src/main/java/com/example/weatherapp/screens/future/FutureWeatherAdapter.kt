package com.example.weatherapp.screens.future

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.screens.model.Weather7Day
import java.util.ArrayList

class FutureWeatherAdapter() : RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder>() {
    private val mListWeather7Day: MutableList<Weather7Day> = ArrayList<Weather7Day>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgWeather)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatusFuture)
        val tvTempMax: TextView = itemView.findViewById(R.id.tvTempMax)
        val tvTempMin: TextView = itemView.findViewById(R.id.tvTempMin)


        fun fillData(weather7Day: Weather7Day){
            imageView.setImageResource(R.drawable.cloud)
            tvDate.text = weather7Day.valid_date
            tvStatus.text = weather7Day.weather.description
            tvTempMax.text = weather7Day.max_temp.toString()
            tvTempMin.text = weather7Day.min_temp.toString()
        }
    }
    fun addData(weather7Day: Weather7Day) {
        mListWeather7Day.add(weather7Day)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {// hàm xử lý item lấy view nào
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//xử lý dữ liệu hiển thị nên view item
       //0-6
        val weather7Day = mListWeather7Day[position] //??????
        holder.fillData(weather7Day)
    }

    override fun getItemCount(): Int {// số item
        return mListWeather7Day.size //7
    }

    fun setData(data: ArrayList<Weather7Day>) {

        /*for(i in 0 until data.size){
            mListWeather7Day.add(data[i])
        }*/

        mListWeather7Day.addAll(data)
        notifyDataSetChanged()
    }
}