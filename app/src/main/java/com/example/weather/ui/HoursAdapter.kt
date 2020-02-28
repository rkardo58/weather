package com.example.weather.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.DegreesModel
import com.example.weather.model.TemperatureModel
import com.example.weather.utils.Utils

class HoursAdapter(context: Context, private val list: List<TemperatureModel>) : RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var mView = view
        var temp : TextView = mView.findViewById(R.id.temp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hour_temp_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HoursAdapter.ViewHolder, position: Int) {
        holder.temp.text = Utils.convertDoubleStringToInt(list[position].main.temp).toString()
    }

}