package com.example.howtousempandroidchart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor

//Tutorial used by this graph type https://medium.com/@codingInformer/how-to-use-mpandroidchart-in-android-studio-c01a8150720f

class BarChartActivity : AppCompatActivity() {

    private var chart: BarChart? = null
    /*private val seekBarX: SeekBar? = null
    private  var seekBarY: SeekBar? = null
    private val tvX: TextView? = null
    private  var tvY: TextView? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)
        setUpChart()
        setData(5, 100f)
    }

    private fun setUpChart(){

        chart = findViewById<BarChart>(R.id.chart1)
        title = "BarChartActivity"
        chart!!.setDrawBarShadow(false)
        chart!!.setDrawValueAboveBar(true)
        chart!!.description.isEnabled = false
        // if more than 60 entries are displayed in the chart, no values will be drawn
        chart!!.setMaxVisibleValueCount(60)
        // scaling can now only be done on x- and y-axis separately
        chart!!.setPinchZoom(false)
        chart!!.setDrawGridBackground(false)
        val l = chart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = Legend.LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
    }

    private fun setData(count: Int, range: Float) {
        val start = 1f
        val values = ArrayList<BarEntry>()
        var i = start.toInt()
        while (i < start + count) {
            val value = (Math.random() * (range + 1)).toFloat()
            //Obs: BarEntry(X,Y) coordinates of graph
            if (Math.random() * 100 < 25) {
                values.add(BarEntry(i.toFloat(), value, resources.getDrawable(R.drawable.star)))
            } else {
                values.add(BarEntry(i.toFloat(), value))
            }
            i++
        }
        val set1: BarDataSet
        if (chart!!.data != null &&
            chart!!.data.dataSetCount > 0
        ) {
            set1 = chart!!.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart!!.data.notifyDataChanged()
            chart!!.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "The year 2017")
            set1.setDrawIcons(false)
            val startColor1 = ContextCompat.getColor(this, R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(this, R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(this, R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(this, R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(this, R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(this, R.color.holo_blue_dark)
            val endColor2 = ContextCompat.getColor(this, R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(this, R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(this, R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(this, R.color.holo_orange_dark)
            val gradientFills: MutableList<GradientColor> = ArrayList()
            gradientFills.add(GradientColor(startColor1, endColor1))
            gradientFills.add(GradientColor(startColor2, endColor2))
            gradientFills.add(GradientColor(startColor3, endColor3))
            gradientFills.add(GradientColor(startColor4, endColor4))
            gradientFills.add(GradientColor(startColor5, endColor5))
            set1.gradientColors = gradientFills
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f
            chart!!.data = data
        }
    }

}