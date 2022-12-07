package com.example.howtousempandroidchart


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    lateinit var chart1 : TextView
    lateinit var chart2 : TextView
    lateinit var chart3 : TextView
    lateinit var chart4 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart1 = findViewById<Button>(R.id.button1)
        chart1.setOnClickListener {
            Intent(this, BarChartActivity::class.java).also {
                startActivity(it)
            }
        }

        chart2 = findViewById<Button>(R.id.button2)
        chart2.setOnClickListener {
            Intent(this, LineChartActivity::class.java).also {
                startActivity(it)
            }
        }

        chart3 = findViewById<Button>(R.id.button3)
        chart3.setOnClickListener {
            Intent(this, LineChart2Activity::class.java).also {
                startActivity(it)
            }
        }

        chart4 = findViewById<Button>(R.id.button4)
        chart4.setOnClickListener {
            Intent(this, PieChartActivity::class.java).also {
                startActivity(it)
            }
        }
    }


}