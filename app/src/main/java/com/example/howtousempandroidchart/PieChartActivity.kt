package com.example.howtousempandroidchart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.howtousempandroidchart.custom.MyCustomFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF


class PieChartActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart

    private var tfRegular: Typeface? = null
    private var tfLight: Typeface? = null

    val TAG = "layon.f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        tfRegular = Typeface.createFromAsset(assets, "OpenSans-Regular.ttf")
        tfLight = Typeface.createFromAsset(assets, "OpenSans-Light.ttf")

        // in this example, a LineChart is initialized from xml
        pieChart = findViewById(R.id.pieChart)

        setData()
    }

    private fun setData() {

        val entries: MutableList<PieEntry> = ArrayList()

        entries.add(PieEntry(20.5f, "EUA"))
        entries.add(PieEntry(18.7f, "EU"))
        entries.add(PieEntry(14.8f, "China"))
        entries.add(PieEntry(4.9f, "Japan"))
        entries.add(PieEntry(1.6f, "Brazil"))

        val set = PieDataSet(entries, "PIB (trillions of dollars)")

        set.setDrawIcons(false)

        set.sliceSpace = 3f
        set.iconsOffset = MPPointF(0f, 40f)
        set.selectionShift = 5f


        // add a lot of colors
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        set.colors = colors
        //set.valueTextSize = 40f

        val data = PieData(set)
        data.setValueFormatter(MyCustomFormatter("US$", ""))
        data.setValueTextSize(14f)
        data.setValueTextColor(Color.BLACK)
        data.setValueTypeface(tfLight)
        pieChart.data = data

        val l: Legend = pieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTypeface(tfRegular)
        pieChart.setEntryLabelTextSize(14f)

        // undo all highlights
        pieChart.highlightValues(null)

        pieChart.setCenterTextTypeface(tfLight)
        pieChart.centerText = generateCenterSpannableText()
        pieChart.description.text = "Sample of how to setup PieChart"

        pieChart.animateY(1400, Easing.EaseInOutQuad)

    }


    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\nby Layon")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 5, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 5, 0)
        s.setSpan(RelativeSizeSpan(.8f), 14, s.length - 5, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 5, s.length, 0)
        return s
    }
}