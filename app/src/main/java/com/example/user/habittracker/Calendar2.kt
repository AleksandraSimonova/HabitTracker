package com.megaplanner.frequency

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar2.*
import java.util.*
import android.widget.CalendarView
import com.example.user.habittracker.R

class Calendar2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar2)
        /*val date = Date(clnd.date)
        date.day */

        var i : Int = 1
        var dat = MutableList<Long>(1){i -> Date(clnd.date).time}
        back.setOnClickListener {
            val intent = Intent(this, Calendar::class.java)
            intent.putExtra("clnds",dat.toTypedArray<Long>())
            startActivity(intent)
        }
        val clndr = findViewById<CalendarView>(R.id.clnd)
        clndr.setOnDateChangeListener { view, year, month, dayOfMonth ->
            if (i == 1) dat.removeAt(0)
            else if (i <= 21) dat.add(Date(year, month, dayOfMonth).time)
            i++
        }
    }
}
