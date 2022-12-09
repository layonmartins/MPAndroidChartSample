package com.example.howtousempandroidchart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.howtousempandroidchart.custom.MyCustomFormatter
import com.example.howtousempandroidchart.custom.MyMarkerView
import com.example.howtousempandroidchart.custom.TimerFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter

class LineChart3Activity : AppCompatActivity() {

    private var chart: LineChart? = null
    private var tfRegular: Typeface? = null
    private var tfLight: Typeface? = null
    val TAG = "layon.f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_chart3)

        chart = findViewById(R.id.cubicLineChart)
        setupChartStyle()
        setupData()
        setupMarker()
    }


    private fun setupChartStyle() {

        //chart?.setViewPortOffsets(0f, 0f, 0f, 0f)
        //chart?.setBackgroundColor(Color.rgb(104, 241, 175))
        chart?.setBackgroundColor(Color.WHITE)

        // no description text
        chart?.description?.isEnabled = false

        // enable touch gestures
        chart?.setTouchEnabled(true)

        // enable scaling and dragging
        chart?.isDragEnabled = true
        chart?.setScaleEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        chart?.setPinchZoom(true)

        chart?.setDrawGridBackground(false)
        chart?.maxHighlightDistance = 300f

        val x = chart?.xAxis
        //x?.isEnabled = false
        x?.typeface = tfRegular
        x?.setDrawGridLines(false)
        x?.position = XAxis.XAxisPosition.BOTTOM //deixa o aixo embaixo
        x?.textColor = Color.BLACK
        x?.axisLineColor = Color.BLACK
        x?.valueFormatter = TimerFormatter()

        tfRegular = Typeface.createFromAsset(assets, "OpenSans-Regular.ttf")
        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")

        val y = chart?.axisRight
        y?.typeface = tfRegular
        y?.setLabelCount(6, false)
        y?.textColor = Color.BLACK
        y?.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        y?.setDrawGridLines(true)
        y?.axisLineColor = Color.BLACK
        y?.valueFormatter = MyCustomFormatter("", "KW")

        chart?.axisLeft?.isEnabled = false // desativa o eixo y de aparecer as legendas do lado esquerdo

        chart?.legend?.isEnabled = false

        chart?.animateXY(1000, 1000)

        // don't forget to refresh the drawing
        chart?.invalidate()

    }

    private fun setupData() {

        val values = ArrayList<Entry>()

        //create static values
        values.add(Entry(60f, 17f ))
        values.add(Entry(90f, 17f ))
        values.add(Entry(120f, 16f ))
        values.add(Entry(150f, 17f ))
        values.add(Entry(180f, 19f ))
        values.add(Entry(210f, 20f ))
        values.add(Entry(240f, 20f ))
        values.add(Entry(270f, 18f ))
        values.add(Entry(300f, 18f ))
        values.add(Entry(330f, 17f ))
        values.add(Entry(360f, 18f ))
        values.add(Entry(390f, 19f ))
        values.add(Entry(410f, 20f ))
        values.add(Entry(440f, 21f ))
        values.add(Entry(470f, 21f ))
        values.add(Entry(500f, 20f ))
        values.add(Entry(530f, 19f ))

        val set1: LineDataSet
        val chartData = chart?.data
        val chartDataCount = chart?.data?.dataSetCount ?: 0
        if (chartData != null && chartDataCount > 0
        ) {
            set1 = chart?.data?.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            chart?.data?.notifyDataChanged()
            chart?.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.1f
            set1.setDrawFilled(true) //preenche a cor interna
            set1.setDrawCircles(false) //desenha ou não os circulos de marcador

            set1.circleRadius = 8f
            set1.circleHoleRadius = 5f //raio da bolinha branca do meio
            set1.lineWidth = 2f
            set1.setCircleColor(resources.getColor(R.color.item_line))
            set1.highLightColor = Color.GRAY
            set1.disableDashedHighlightLine()
            set1.color = resources.getColor(R.color.item_line) //line color
            set1.fillColor = resources.getColor(R.color.item_fill) //cor do preenchimento
            set1.fillAlpha = 255 // beteween 0 - 255
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { _, _ -> (chart?.axisLeft?.axisMinimum ?: null) as Float }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTypeface(tfLight)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            chart?.data = data
        }
    }

    //obs: I can not hide the values circle and show only when selected, so I add a marker with the circle
    private fun setupMarker() {
        //setup markerView
        val marker = MyMarkerView(this@LineChart3Activity, R.layout.custom_marker_view)
        //marker.offset = MPPointF(0f, 0f)
        chart?.marker = marker
    }

}