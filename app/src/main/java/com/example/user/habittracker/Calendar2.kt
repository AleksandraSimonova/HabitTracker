package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calendar2.*
import java.util.*
import android.widget.CalendarView
import com.example.user.habittracker.R
import kotlinx.android.synthetic.main.activity_main2.*

class Calendar2 : AppCompatActivity() {

    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar2)
        /*val date = Date(clnd.date)
        date.day */

        var i : Int = 1
        var dat = Date(clnd.date)
        back.setOnClickListener {
            if (dat != null) {
                val res = Intent()
                res.putExtra(Calendar2.EXTRA_TASK_DESCRIPTION, "${dat.date}.${dat.month+1}.${dat.year}")
                setResult(Activity.RESULT_OK, res)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
        val clndr = findViewById<CalendarView>(R.id.clnd)
        clndr.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dat = Date(year,month,dayOfMonth)
        }

    }

}
