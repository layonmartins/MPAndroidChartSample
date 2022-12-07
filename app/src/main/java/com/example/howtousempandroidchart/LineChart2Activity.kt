package com.example.howtousempandroidchart

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.howtousempandroidchart.custom.MyMarkerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.MPPointF


class LineChart2Activity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    val TAG = "layon.f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_chart2)

        // in this example, a LineChart is initialized from xml
        lineChart = findViewById(R.id.lineChart)

        setData()
        setStyle()
    }

    // See the documentation of how setup data on charts https://weeklycoding.com/mpandroidchart-documentation/setting-data/
    private fun setData() {

        //create the list that will hold the chart data as Entry object
        val valuesComp1 = ArrayList<Entry>()
        val valuesComp2 = ArrayList<Entry>()

        // fill the lists with Entry objects
        val c1e1 = Entry(0f, 100000f) // 0 == quarter 1
        val c1e2 = Entry(1f, 140000f) // 1 == quarter 2
        val c1e3 = Entry(2f, 120000f) // 2 == quarter 3
        val c1e4 = Entry(3f, 150000f) // 3 == quarter 4
        val c1e5 = Entry(4f, 180000f) // 4 == quarter 5

        valuesComp1.addAll(listOf (c1e1, c1e2, c1e3, c1e4, c1e5))

        val c2e1 = Entry(0f, 130000f) // 0 == quarter 1
        val c2e2 = Entry(1f, 115000f) // 1 == quarter 2
        val c2e3 = Entry(2f, 80000f) // 2 == quarter 3
        val c2e4 = Entry(3f, 105000f) // 3 == quarter 4
        val c2e5 = Entry(4f, 90000f) // 4 == quarter 5

        valuesComp2.addAll(listOf (c2e1, c2e2, c2e3, c2e4, c2e5))

        Log.d(TAG, valuesComp1.toString())
        Log.d(TAG, valuesComp2.toString())


        // Now that we have our lists of Entry objects,
        // the LineDataSet objects can be created:
        val lineDataSetComp1 = LineDataSet(valuesComp1, "Company 1")
        lineDataSetComp1.axisDependency = YAxis.AxisDependency.LEFT
        lineDataSetComp1.setColors(
            intArrayOf(
                R.color.holo_blue_bright
            ), this
        )


        val lineDataSetComp2 = LineDataSet(valuesComp2, "Company 2")
        lineDataSetComp2.axisDependency = YAxis.AxisDependency.LEFT
        lineDataSetComp2.setColors(
            intArrayOf(
                R.color.holo_red_light
            ), this
        )

        // Last but not least, we create a list of IDataSets and build our ChartData object:
        // use the interface ILineDataSet
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSetComp1)
        dataSets.add(lineDataSetComp2)

        val data = LineData(dataSets)
        lineChart.data = data

        // Setup XAxis
        // This class allows custom styling of values drawn on the XAxis (and more). In this example, the formatter could look like this:
        // the labels that should be drawn on the XAxis
        val years = listOf("1982", "1992", "2002", "2012", "2022")
        val formatter: LargeValueFormatter = object : LargeValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                return years[value.toInt()]
            }
        }
        val xAxis = lineChart.xAxis
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter
        xAxis.setDrawGridLines(false) //not draw the line XAxis
        xAxis.setDrawAxisLine(false)

        // Setup YAxis
        val leftAxis: YAxis = lineChart.axisLeft
        val rightAxis: YAxis = lineChart.axisRight
        leftAxis.isEnabled = false
        rightAxis.isEnabled = false

        // After calling invalidate() the chart is refreshed and the provided data is drawn.
        //lineChart.invalidate()


        // if call animate. not need call invalidate()
        lineChart.animateY(1000, Easing.EaseOutCubic)
    }

    private fun setStyle() {


        lineChart.apply {

            // setLogEnabled(boolean enabled): Setting this to true will activate chart logcat output.
            // Enabling this is bad for performance, keep disabled if not necessary.
            isLogEnabled = true

            description = Description().also {
                it.text = "Profit for 10 years"
                it.textAlign = Paint.Align.LEFT
                it.setPosition(620f, 620f)
                it.textSize = 15f
            }

            isAutoScaleMinMaxEnabled = true
            isKeepPositionOnRotation = true

            //setup markerView
            val marker = MyMarkerView(this@LineChart2Activity, R.layout.custom_marker_view)
            //marker.offset = MPPointF(0f, 0f)
            setMarker(marker)

            invalidate()
        }


    }
}