package com.example.user.habittracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.user.habittracker.R


class Notification : AppCompatActivity() {

    var tm = String()

    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
    }

}

