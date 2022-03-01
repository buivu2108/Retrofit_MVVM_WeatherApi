package com.example.weatherapp.screens.future

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //
    private val listData = arrayListOf("1", "2", "3", "4")

    fun addData(data: String){
        listData.add(data)
        notifyDataSetChanged()
    }

    class TestViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.tvTest)

        fun fillData(data: String){
            textView.text = data
        }
    }

    class Test2ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.tvTest)

        fun fillData(data: String){
            textView.text = data
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 0){
            //tao layout chan -> item mau do
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
            return TestViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test_2, parent, false)
            return Test2ViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is Test2ViewHolder){
            //data theo kieu le
            (holder as Test2ViewHolder).fillData(listData[position])

        } else {
            //data theo kieu chan
            (holder as TestViewHolder).fillData(listData[position])
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        //check position chan //type do le typexanh
        if(position % 2 == 0){
            //chan
            return 0
        } else {
            return 1
        }
    }
}