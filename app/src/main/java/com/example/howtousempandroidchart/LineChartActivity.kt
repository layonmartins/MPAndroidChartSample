package com.example.howtousempandroidchart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils

class LineChartActivity : AppCompatActivity(), OnChartValueSelectedListener {

    private var chart: LineChart? = null
    private var tfRegular: Typeface? = null
    private var tfLight: Typeface? = null
    private lateinit var generateDataButton: Button
    val TAG = "layon.f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_chart)

        setupChartStyle()
        setupAxisAndLimitLines()
        setupData()

        generateDataButton = findViewById(R.id.generateDataButton)
        generateDataButton.setOnClickListener {
            setupData()
        }

    }

    private fun setupData() {
        setData(10, 30f)

        // draw points over time
        chart?.animateX(1000)

        // get the legend (only possible after setting data)
        val l = chart?.legend

        // draw legend entries as lines
        l?.form = LegendForm.LINE
    }

    private fun setData(count: Int, range: Float) {
        val values = ArrayList<Entry>()

        //random values
        for (i in 0 until count) {
            val `val` = (Math.random() * range).toFloat()
            values.add(Entry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
        }

        //create status values
        /*values.add(Entry(0f, 1f, resources.getDrawable(R.drawable.star)))
        values.add(Entry(1f, 5f, resources.getDrawable(R.drawable.star)))
        values.add(Entry(2f, 20f, resources.getDrawable(R.drawable.star)))
        values.add(Entry(3f, 15f, resources.getDrawable(R.drawable.star)))
        values.add(Entry(3f, 10f, resources.getDrawable(R.drawable.star)))*/

        val set1: LineDataSet
        val chartData = chart?.data
        val chartDataCount = chart?.data?.dataSetCount ?: 0
        if (chartData != null && chartDataCount > 0
        ) {
            set1 = chart?.data?.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart?.data?.notifyDataChanged()
            chart?.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.setDrawIcons(false)

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { _, _ -> (chart?.axisLeft?.axisMinimum ?: null) as Float }

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(this, R.drawable.fade_orange)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            chart?.data = data
        }
    }

    private fun setupChartStyle() {
        // // Chart Style // //
        chart = findViewById(R.id.lineChart)
        chart?.let {
            // background color
            chart?.setBackgroundColor(Color.WHITE)

            // disable description text
            it.description.isEnabled = false


            // enable touch gestures
            it.setTouchEnabled(true)


            // set listeners
            chart?.setOnChartValueSelectedListener(this)
            chart?.setDrawGridBackground(false)

            // create marker to display box when values are selected
            val mv = MarkerView(this, R.layout.custom_marker_view)

            // Set the marker to the chart
            mv.chartView = chart
            chart?.marker = mv

            // enable scaling and dragging
            chart?.isDragEnabled = true
            chart?.setScaleEnabled(true)
            // chart.setScaleXEnabled(true)
            // chart.setScaleYEnabled(true)

            // force pinch zoom along both axis
            // chart.setScaleXEnabled(true)
            // chart.setScaleYEnabled(true)

            // force pinch zoom along both axis
            chart?.setPinchZoom(true)
        }

    }

    private fun setupAxisAndLimitLines() {
        // // X-Axis Style // //
        val xAxis: XAxis? = chart?.xAxis

        // vertical grid lines
        xAxis?.enableGridDashedLine(10f, 10f, 0f)

        // // Y-Axis Style // //
        val yAxis: YAxis? = chart?.axisLeft

        // disable dual axis (only use LEFT axis)
        chart?.axisRight?.isEnabled = false

        // horizontal grid lines
        yAxis?.enableGridDashedLine(10f, 10f, 0f)

        // axis range
        yAxis?.axisMaximum = 40f
        yAxis?.axisMinimum = 0f

        // // Create Limit Lines // //

        tfRegular = Typeface.createFromAsset(assets, "OpenSans-Regular.ttf")
        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")

        val llXAxis = LimitLine(9f, "Index 10")
        llXAxis.lineWidth = 4f
        llXAxis.enableDashedLine(10f, 10f, 0f)
        llXAxis.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        llXAxis.textSize = 10f
        llXAxis.typeface = tfRegular

        val ll1 = LimitLine(30f, "Upper Limit")
        ll1.lineWidth = 4f
        ll1.enableDashedLine(10f, 10f, 0f)
        ll1.labelPosition = LimitLabelPosition.RIGHT_TOP
        ll1.textSize = 10f
        ll1.typeface = tfRegular

        val ll2 = LimitLine(1f, "Lower Limit")
        ll2.lineWidth = 4f
        ll2.enableDashedLine(10f, 10f, 0f)
        ll2.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        ll2.textSize = 10f
        ll2.typeface = tfRegular


        // draw limit lines behind data instead of on top
        yAxis?.setDrawLimitLinesBehindData(true)
        xAxis?.setDrawLimitLinesBehindData(true)


        // add limit lines
        yAxis?.addLimitLine(ll1)
        yAxis?.addLimitLine(ll2)
        //xAxis.addLimitLine(llXAxis);

    }

    override fun onValueSelected(e: Entry, h: Highlight?) {
        Log.i(TAG,"Entry selected $e")
        Log.i(TAG, "LOW HIGH - low: " + chart?.lowestVisibleX + ", high: " + chart?.highestVisibleX)
        Log.i(
            TAG, "MIN MAX - xMin: " + chart?.xChartMin + ", xMax: " + chart?.xChartMax + ", yMin: " + chart?.yChartMin + ", yMax: " + chart?.yChartMax
        )
    }

    override fun onNothingSelected() {
        Log.i(TAG, "Nothing selected - Nothing selected.")
    }
}