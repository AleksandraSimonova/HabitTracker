package com.example.user.habittracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {

    var num:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        var data = mutableListOf<String>()

        //var data = MutableList<String>(1){"Нажми на меня"}
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        habitlist.adapter = adapter

        add.setOnClickListener {
            num++
            data.add("Нажми на меня")
            adapter.notifyDataSetChanged()
            habitlist.adapter = adapter
            registerForContextMenu(habitlist)
        }
        /*habitlist.setOnItemClickListener { parent, view, position, id ->
            //ArinA, where is your code????
        }*/

    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Выбери:")
        menu!!.add("Сделал!")
        menu!!.add("Пропустить...")

    }

}


