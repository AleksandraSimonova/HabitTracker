package com.example.user.habittracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //var arr = emptyArray<String>()

        var d = MySqlHelper1.getInstance(this)

        var label : String = ""
        val name = edittext.text.toString()
        var freq = 0

        addnot.setOnClickListener {
            val intent = Intent(this, Notification::class.java)

            startActivity(intent)
        }

        button1.setOnClickListener{
            if (freq != 0)
                freq--
            freqcount.text = freq.toString()
        }

        button2.setOnClickListener{
            if (freq < 6)
                freq++
            freqcount.text = freq.toString()
        }

        date.setOnClickListener{
            val intent = Intent(this, Calendar2::class.java)

            startActivity(intent)

        }

        var data = mutableListOf<String>("Учёба", "Работа", "Саморазвитие")
        var adapter = ArrayAdapter<String>(this, R.layout.my_spinner_item, data)
        spinner.adapter = adapter
        /*spinner.onItemSelectedListener { parent, view, position, id ->
            label = spinner.selectedItem.toString()
        }*/

        ok.setOnClickListener {
            d.insert(this, label, name, freq)
            finish()
        }

        //spinner.onItemSelectedListener{
        //
        //}
    }
}
