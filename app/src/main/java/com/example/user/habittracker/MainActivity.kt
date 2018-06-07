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
import kotlinx.android.synthetic.main.activity_table.*
import kotlinx.android.synthetic.main.grid.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        list_habits()
        grid_head()
        grid_table()

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

    // Table with results
    // count of days
    // count of habits
    fun grid_table(days : Int = 10, habits : Int = 3)
    {
        var table_size = days * habits

        gridview.columnCount = days
        gridview.rowCount = habits


        for (i in 1..table_size) {
            val text = TextView(this)
            text.width = 60
            text.height = 70
            text.text = ""
            text.setBackgroundResource(R.drawable.element_green)


            gridview.addView(text)

        }
    }



    // grid witg dates
    fun grid_head(days : Int = 10){

        headgridview.columnCount = days
        headgridview.rowCount = 1


        for (i in 1..days) {
            val text = TextView(this)
            text.width = 60
            text.height = 70
            text.setBackgroundColor(Color.GREEN)
            text.text = "1"
            text.setBackgroundResource(R.drawable.element_green)


            gridview.addView(text)

        }


    }

    fun list_habits(){

        var habit = mutableListOf<String>("Habits", "Sport", "Reading")
        var adapter_habit = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, habit)


        listview.adapter = adapter_habit

    }



}
