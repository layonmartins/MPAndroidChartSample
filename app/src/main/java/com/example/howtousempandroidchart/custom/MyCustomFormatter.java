package com.example.howtousempandroidchart.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * This IValueFormatter is just for convenience and simply puts a "%" sign after
 * each value. (Recommeded for PieChart)
 *
 * @author Philipp Jahoda
 */
public class MyCustomFormatter extends ValueFormatter
{

    protected DecimalFormat mFormat;
    protected String mPrefix;
    protected String mSuffix;

    public MyCustomFormatter(String prefix, String suffix) {
        mFormat = new DecimalFormat("###,###,##0.0");
        this.mPrefix = prefix;
        this.mSuffix = suffix;
    }

    /**
     * Allow a custom decimalformat
     *
     * @param format
     */
    public MyCustomFormatter(DecimalFormat format, String prefix, String suffix) {
        this.mFormat = format;
        this.mPrefix = prefix;
        this.mSuffix = suffix;
    }



    @Override
    public String getFormattedValue(float value) {
       // return super.getFormattedValue(value);
        return mPrefix + mFormat.format(value) + mSuffix;
    }

    public int getDecimalDigits() {
        return 1;
    }
}
