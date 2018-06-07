package com.example.user.habittracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        addnot.setOnClickListener {
//            /*val intent = Intent(this, Main2Activity::class.java)
//            intent.putExtra("data_id", "data")
//            startActivity(intent)*/
//        }

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

        var data = mutableListOf<String>("Учёба", "Работа", "Саморазвитие")
        var adapter = ArrayAdapter<String>(this, R.layout.my_spinner_item, data)
        spinner.adapter = adapter
        //spinner.onItemSelectedListener{
        //    
        //}
    }
}
