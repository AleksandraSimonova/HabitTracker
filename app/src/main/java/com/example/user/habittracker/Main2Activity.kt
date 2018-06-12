package com.example.user.habittracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.text.DateFormat
import java.util.*
import java.time.*

class Main2Activity : AppCompatActivity() {

    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    }

    val ADD_TASK_REQUEST : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        addnot.setOnClickListener {

        }

        var ch = 0



        button1.setOnClickListener{
            if (ch != 0)
                ch--
            freqcount.text = ch.toString()
        }

        button2.setOnClickListener{
            if (ch < 6)
                ch++
            freqcount.text = ch.toString()
        }

        date.setOnClickListener{
            val intent = Intent(this, Calendar2::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)

        }

        okbut.setOnClickListener {
            val habit = name.text.toString()
            if (!habit.isEmpty()) {
                val res = Intent()
                res.putExtra(EXTRA_TASK_DESCRIPTION, habit)
                setResult(Activity.RESULT_OK, res)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
        var data = mutableListOf<String>("Учёба", "Работа", "Саморазвитие")
        var adapter = ArrayAdapter<String>(this, R.layout.my_spinner_item, data)
        spinner.adapter = adapter

        /*spinner.onItemSelectedListener{
                   In code we trust
        */
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val datt = data?.getStringExtra(Main2Activity.EXTRA_TASK_DESCRIPTION)
                datt?.let()
                {
                    date.text = datt
                }
            }
        }
    }
}
