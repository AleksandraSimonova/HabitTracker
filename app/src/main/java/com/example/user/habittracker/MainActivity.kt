package com.example.user.habittracker

import android.graphics.Color
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grid.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var habit = mutableListOf<String>("Habits", "Sport", "Reading")
        var adapter_habit = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, habit)


        // count of days
        var days = 100
        // count of habits
        var habits = 100

        var table_size = days * habits


        gridview.columnCount = days
        gridview.rowCount = habits


            for (i in 1..table_size) {
                val text = TextView(this)
                text.width = 30
                text.height = 30
                text.setTextColor(Color.BLACK)
                text.setBackgroundColor(Color.WHITE)
                text.text = "1"


                gridview.addView(text)

                val v = View(this)
                v.layoutParams = ViewGroup.LayoutParams(3, ViewGroup.LayoutParams.MATCH_PARENT)
                v.setBackgroundColor(Color.BLACK)
                gridview.addView(v)

                val v1 = View(this)
                v1.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3)
                v1.setBackgroundColor(Color.BLACK)
                gridview.addView(v1)

            }


        listview.adapter = adapter_habit

        textview.setTextColor(Color.BLACK)


        //var data = Array(100){i->(i+1).toString()}
        //var adapter_data = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        /*button.setOnClickListener{
            data.add(button.text.toString())
            adapter.notifyDataSetChanged()

        } */

        /* var grid = mutableListOf<String>()
        for (i in 1..90)
            grid.add("")
        var adapter_grid = ArrayAdapter<String>(this, R.layout.grid, grid)*/
        //headgridview.adapter = adapter_data
    }

}
