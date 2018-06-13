package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {

    var num:Int = 0

    val dat : MutableList<String> = mutableListOf()

    val adapter by lazy {makeAdapter(dat)}

    val ADD_TASK_REQUEST = 1

    var current_id = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        habitlist.adapter = adapter

        registerForContextMenu(habitlist)
        habitlist.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            current_id = position
        }
        add.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }

        statistic.setOnClickListener {
            val intent = Intent(this, HabitTable::class.java)
            startActivity(intent)
        }

        settings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        /*habitlist.setOnItemClickListener { parent, view, position, id ->
            No code we need
        }*/

    }
    fun makeAdapter(list: List<String>): ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val habit = data?.getStringExtra(Main2Activity.EXTRA_TASK_DESCRIPTION)
                habit?.let()
                {
                    dat.add(habit)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Выбери:")
        menu!!.add("Сделано!")
        menu!!.add("Пропустить...")
        menu!!.add("Удалить")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (current_id >= 0) {
            if (item.toString() == "Сделано!")
                dat[current_id] += " - Сделано"
            else if (item.toString() == "Пропустить...")
                dat[current_id] += " - Пропущено"
            else if (item.toString() == "Удалить")
                dat.removeAt(current_id)
            adapter.notifyDataSetChanged()
        }
        current_id = -1
        return super.onContextItemSelected(item)
    }

}


