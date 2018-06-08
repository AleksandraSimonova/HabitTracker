package com.example.user.habittracker

import android.graphics.Color
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_table.*
import kotlinx.android.synthetic.main.grid.*
import java.util.*

public class HabitTable() : AppCompatActivity(){

    var days = 10
    var habits = 100
    var flag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        Run()
    }



    fun Run()
    {
        var array = Array(habits+1){i -> Array<TextView>(days){i -> TextView(this)}}

        list_habits()
        grid_table(array)
    }

    fun list_habits()
    {

        var habit = mutableListOf<String>("Swimming", "Sport", "Reading")
        var adapter_habit = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, habit)


        listview.adapter = adapter_habit

    }

    fun grid_table(arr : Array<Array<TextView>>)
    {
        gridview.columnCount = days
        gridview.rowCount = habits + 1

        val viewG = listview
        val listItem = listview.adapter.getView(0, null, viewG)
        listItem.measure(0,0)

        if (!listview.adapter.isEmpty)
        {
            for (h in 0 until (habits + 1))
                for (d in 0 until days)
                {
                    arr[h][d].width = listItem.measuredHeight
                    arr[h][d].height = listItem.measuredHeight
                    arr[h][d].text = ""
                    arr[h][d].setBackgroundResource(R.drawable.element_default)


                    gridview.addView(arr[h][d])

                }
            //set_data(arr)
            //grid_change(arr)
       }
    }

    fun grid_change(arr : Array<Array<TextView>>)
    {
        val viewG = listview
        val listItem = listview.adapter.getView(0, null, viewG)
        listItem.measure(0,0)

        if (!listview.adapter.isEmpty)
        {

            if (flag == true)
                arr[0][0].setBackgroundResource(R.drawable.element_green)
            else
                arr[0][0].setBackgroundResource(R.drawable.element_red)

            gridview.invalidate();
        }

    }



    fun set_data(arr : Array<Array<TextView>>, iYear : Int = 2018, iMonth : Int = java.util.Calendar.JUNE, iDay : Int = 1)
    {

        var data = GregorianCalendar(iYear, iMonth, iDay)

        var amount_days = data.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)

        for (i in 0..(amount_days - 1))
        {
            arr[0][i].text = i.toString()

        }

    }

}
