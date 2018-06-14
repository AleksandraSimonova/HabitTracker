package com.example.user.habittracker

import android.app.*
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main_page.*
import android.app.PendingIntent
import android.media.RingtoneManager


class MainPage : AppCompatActivity() {

    var num:Int = 0

    val dat : MutableList<String> = mutableListOf()

    val adapter by lazy {makeAdapter(dat)}

    val ADD_TASK_REQUEST = 1

    var current_id = -1

    fun habitSelected(position: Int)
    {
        AlertDialog.Builder(this)
                .setTitle(dat[position])
                .setMessage("Дата начала: ----\nЧастота: ----\nВремя: ----\nВибрация: ---\nМузыка: ------")
                .setNegativeButton(R.string.Cancel,{
                    dialog, _ -> dialog.cancel()
                })
                .setPositiveButton(R.string.Status,
                        {
                            dialog, _ ->
                            AlertDialog.Builder(this)
                                    .setTitle("Изменить статус привычки")
                                    .setMessage("Вы можете изменить статус привычки ${dat[position]}")
                                    .setNeutralButton(R.string.Cancel,
                                            {
                                                dialog, _ -> dialog.cancel()
                                            })
                                    .setPositiveButton(R.string.Done,
                                            {
                                                dialog, _->
                                                dat[position] += " - Сделано"
                                                adapter.notifyDataSetChanged()
                                                dialog.cancel()
                                            })
                                    .setNegativeButton(R.string.Skip,
                                            {
                                                dialog, _ ->
                                                dat[position] += " - Пропуск"
                                                adapter.notifyDataSetChanged()
                                                dialog.cancel()
                                            })
                                    .create()
                                    .show()
                        })
                .setNeutralButton(R.string.Delete,
                        {
                            dialog, _ ->
                            dat.removeAt(position)
                            adapter.notifyDataSetChanged()
                            dialog.cancel()
                        })
                .create()
                .show()
    }
// Initial commit
    fun showNotification() {
        println("I am still work")
        /*val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT)
        var builder : NotificationCompat.Builder = NotificationCompat.Builder(this)
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.not_icon)
                .setContentTitle("Привычка ждет!")
                .setWhen(System.currentTimeMillis())
                .setContentText("Здесь будет вопрос?")
        // var notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notMang : NotificationManagerCompat = NotificationManagerCompat.from(this)
        notMang.notify(0, builder.build())*/
    val intent = Intent(applicationContext, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val notificationBuilder = NotificationCompat.Builder(applicationContext)
            .setContentText("Dummy Notification")
            .setAutoCancel(true)
            .setSound(uri)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        statistic.imageAlpha = R.mipmap.settings_icon
        habitlist.adapter = adapter

        registerForContextMenu(habitlist)
        habitlist.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            current_id = position
            habitSelected(position)
            //showNotification()
        }
        add.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }
        statistic.setOnClickListener {
            val intent = Intent(this, HabitTable::class.java)
            startActivity(intent)
        }

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

}


