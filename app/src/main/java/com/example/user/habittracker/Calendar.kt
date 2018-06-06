package com.megaplanner.frequency

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.user.habittracker.R
import kotlinx.android.synthetic.main.activity_calendar.*
import java.time.LocalDate
import java.util.*

class Calendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        var i : Int = 1
        var dat : MutableList<Long>
        var getIntentFromA2 = intent
        if(getIntentFromA2.hasExtra("clnds"))
            dat = getIntentFromA2.getLongArrayExtra("clnds").toMutableList()
        ch1.setOnClickListener {
            ch2.isChecked = false
            ch3.isChecked = false
            ch4.isChecked = false
            ch5.isChecked = false


        }
        ch2.setOnClickListener {
            ch1.isChecked = false
            ch3.isChecked = false
            ch4.isChecked = false
            ch5.isChecked = false
        }
        ch3.setOnClickListener {
            ch1.isChecked = false
            ch2.isChecked = false
            ch4.isChecked = false
            ch5.isChecked = false
        }
        ch4.setOnClickListener {
            ch1.isChecked = false
            ch2.isChecked = false
            ch3.isChecked = false
            ch5.isChecked = false
        }
        ch5.setOnClickListener {
            ch1.isChecked = false
            ch2.isChecked = false
            ch3.isChecked = false
            ch4.isChecked = false
            val intent = Intent(this, Calendar2::class.java)
            startActivity(intent)
        }
    }

}
