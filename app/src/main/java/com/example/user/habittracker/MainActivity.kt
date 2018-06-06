package com.example.user.habittracker

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var habit = mutableListOf<String>("Habits", "Sport", "Reading")
        var adapter_habit = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, habit)

        var data = Array(100){i->(i+1).toString()}
        var adapter_data = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        /*button.setOnClickListener{
            data.add(button.text.toString())
            adapter.notifyDataSetChanged()

        } */

        
        var grid = emptyArray<String>()
        var adapter_grid = ArrayAdapter<String>(this, R.layout.grid, grid)
        gridview.setAdapter(adapter_grid)

        headgridview.adapter = adapter_data
        listview.adapter = adapter_habit

        textview.setBackgroundColor(Color.GREEN)
        textview.setTextColor(Color.BLACK)
    }
}
