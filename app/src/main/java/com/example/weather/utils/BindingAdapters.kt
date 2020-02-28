

package com.example.weather.utils

//import com.bumptech.glide.Glide

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.weather.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter


//import com.superapps.ricardo.tablepro.R

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfNullOrEmpty")
fun goneIfNullOrEmpty(view: TextView, it: String?) {
    view.visibility = if (it.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("setGraph")
fun setGraph(view: LineChart, it: LineDataSet?) {
    if (it != null){
        view.data = LineData(it)
        view.data.setDrawValues(false)
        view.setScaleEnabled(true)
        view.setPinchZoom(true)
        view.animateX(1000)
        view.xAxis.setDrawLabels(false)
        view.xAxis.setDrawGridLines(false)
        view.xAxis.setDrawAxisLine(false)
        view.axisRight.valueFormatter = MyYAxisValueFormatter()
        view.axisRight.gridColor = R.color.lightGrey
        view.axisRight.setDrawAxisLine(false)
        view.axisLeft.setDrawGridLines(false)
        view.axisLeft.setDrawLabels(false)
        view.axisLeft.setDrawAxisLine(false)
        view.description.isEnabled = false
        view.background = ContextCompat.getDrawable(view.context, R.drawable.graph_background)
        view.invalidate()
    }
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    //Glide.with(imageView.context).load(url).centerInside().placeholder(R.drawable.ic_dice).into(imageView)
}
