package com.example.user.habittracker

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_settings.*



class Settings() : AppCompatActivity(){

    companion object {
        var labels = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var num = 0

        var adapter = ArrayAdapter<String>(this, R.layout.my_listlabels_item, labels)
        labelslist.adapter = adapter
        addlabel.setOnClickListener {
            if (num < 10) {
                labels.add(edittext.text.toString())
                num++
                val adapter1 = ArrayAdapter<String>(applicationContext, R.layout.my_listlabels_item, labels)
                labelslist.adapter = adapter1
                println(labels)
            }
        }

        labelslist.setOnItemClickListener { parent, view, position, id ->

            val builder = AlertDialog.Builder(this)

            // Set the alert dialog title
            builder.setTitle("Удаление")

            // Display a message on alert dialog
            builder.setMessage("Удалить категорию?")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Да") { dialog, which ->
                labels.removeAt(position)
            }

            // Set a positive button and its click listener on alert dialog
            builder.setNegativeButton("Нет"){ dialog, which ->

            }

            println(position)

        }
    }
}
