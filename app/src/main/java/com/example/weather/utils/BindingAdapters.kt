

package com.example.weather.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.weather.R
import com.example.weather.ui.MainViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@BindingAdapter("goneIfNotLoading")
fun goneIfNotLoading(view: View, it: MainViewModel.StatusEnum?) {
    view.visibility = if (it == MainViewModel.StatusEnum.LOADING) View.VISIBLE else View.GONE
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
