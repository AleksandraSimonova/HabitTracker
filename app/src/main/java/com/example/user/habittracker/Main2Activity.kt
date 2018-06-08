package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    val Calendar2RequestCode = 1
    var day:Int = 0
    var month:Int = 0
    var year:Int = 0


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Calendar2RequestCode && resultCode == Activity.RESULT_OK && data != null)

        {
            day = data.getIntExtra("Day", 0)
            month = data.getIntExtra("Month", 0)
            year = data.getIntExtra("Year", 0)

            val selectedDate = StringBuilder().append(data.getIntExtra("Day",0).toString())
                    .append("-").append((data.getIntExtra("Month",0)+1).toString()).append("-").append(data.getIntExtra("Year",0).toString())
                    .append(" ").toString()
            date.text = selectedDate

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        addnot.setOnClickListener {
            val intent = Intent(this, Notification::class.java)

            startActivity(intent)
        }

        var a = 0

        button1.setOnClickListener{
            if (a != 0)
                a--
            freqcount.text = a.toString()
        }

        button2.setOnClickListener{
            if (a < 6)
                a++
            freqcount.text = a.toString()
        }

        date.setOnClickListener{
            val intent = Intent(this, Calendar2::class.java)

            startActivityForResult(intent, Calendar2RequestCode)

        }

        ok.setOnClickListener {
            finish()
        }
        var data = mutableListOf<String>("Учёба", "Работа", "Саморазвитие")
        var adapter = ArrayAdapter<String>(this, R.layout.my_spinner_item, data)
        spinner.adapter = adapter

        //spinner.onItemSelectedListener{
        //
        //}
    }
}
