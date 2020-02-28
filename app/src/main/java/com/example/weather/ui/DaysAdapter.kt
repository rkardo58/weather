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

class DaysAdapter(private val context: Context, private val lists: List<List<TemperatureModel>>) : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var mView = view
        var day : TextView = mView.findViewById(R.id.day_tv)
        var tempsRecyclerView : RecyclerView = mView.findViewById(R.id.hour_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.day_temp_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: DaysAdapter.ViewHolder, position: Int) {
        holder.day.text = Utils.dateToString(lists[position][0].dt_txt)
        val adapter = HoursAdapter(context, lists[position])
        holder.tempsRecyclerView.adapter = adapter
    }

}