package com.example.howtousempandroidchart.custom

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat
import kotlin.math.roundToInt

class TimerFormatter : ValueFormatter() {

    protected var mFormat: DecimalFormat? = null

    fun MyCustomFormatter() {
        mFormat = DecimalFormat("###,###,##0.0")
    }

    /**
     * Allow a custom decimalformat
     *
     * @param format
     */
    fun MyCustomFormatter(format: DecimalFormat?) {
        mFormat = format
    }


    override fun getFormattedValue(value: Float): String? {
        // return super.getFormattedValue(value);
        val hours: Int = (value / 60).roundToInt() //since both are ints, you get an int
        val minutes: Int = (value % 60).roundToInt()
        return "$hours:$minutes"
    }

    fun getDecimalDigits(): Int {
        return 1
    }
}