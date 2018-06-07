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


public class HabitTable() : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)


        Run()
        //var data = Array(100){i->(i+1).toString()}
        //var adapter_data = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        /* var grid = mutableListOf<String>()
        for (i in 1..90)
            grid.add("")
        var adapter_grid = ArrayAdapter<String>(this, R.layout.grid, grid)*/
    }

    fun Run()
    {
        list_habits()
        grid_table()
    }


    // Table with results
    // amount of days
    // amount of habits
    fun grid_table(days : Int = 10, habits : Int = 3, flag : Boolean = true)
    {
        gridview.columnCount = days
        gridview.rowCount = habits + 1

        /*arr[0][0]

        val exampleText = TextView(this)
        exampleText.text = "Hello world"
        gridview.addView(exampleText)
        exampleText.text = "!"
        gridview.invalidate()*/

        val arr = Array(habits+1){i -> Array<TextView>(days){i -> TextView(this)}}

        for (h in 0 until (habits + 1))
            for (d in 0 until days)
            {
                val text = TextView(this)
                text.width = 60
                text.height = 75
                text.text = ""
                text.setBackgroundResource(R.drawable.element_default)

                arr[h][d] = text
                gridview.addView(text)
            }

    }


    // Grid with dates
    // (day : Int) amount of days
    /*fun grid_head(days : Int = 10){

        headgridview.columnCount = days
        headgridview.rowCount = 1


        for (i in 1..days) {
            val text = TextView(this)
            text.width = 60
            text.height = 70
            text.setBackgroundColor(Color.GREEN)
            text.text = "1"
            text.setBackgroundResource(R.drawable.element_green)


            headgridview.addView(text)

        }

    }*/

    // List with habits
    // massive with habits
    fun list_habits(){

        var habit = mutableListOf<String>("Swimming", "Sport", "Reading")
        var adapter_habit = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, habit)


        listview.adapter = adapter_habit

    }


}
