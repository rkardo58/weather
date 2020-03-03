package com.example.weather.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.TemperatureModel
import com.example.weather.utils.convertDoubleStringToInt
import com.example.weather.utils.getDate
import java.util.*

class HoursAdapter(private val context: Context, private val list: List<TemperatureModel>) : RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var mView = view
        var temp : TextView = mView.findViewById(R.id.temp_value)
        var hour : TextView = mView.findViewById(R.id.hour_value)
        var tempImage : ImageView = mView.findViewById(R.id.temp_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hour_temp_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.temp.text = "${item.main.temp.convertDoubleStringToInt()}º"
        holder.hour.text = "${item.date.getDate().get(Calendar.HOUR_OF_DAY)}h"
        if (item.weather.isNotEmpty()){
            setImage(holder.tempImage, item.weather[0].id)
            holder.tempImage.contentDescription = item.weather[0].description
        }
    }

    private fun setImage(view: ImageView, id: Int) {

        view.setImageDrawable( ContextCompat.getDrawable(context, when{
            id > 800 -> R.drawable.cloud_icon
            id == 800 -> R.drawable.sun_icon
            id >= 700 -> R.drawable.mist_icon
            id >= 600 -> R.drawable.snow_icon
            id >= 300 -> R.drawable.rain_icon
            id >= 200 -> R.drawable.thunder_icon
            else -> R.drawable.mist_icon
        }))
    }

}