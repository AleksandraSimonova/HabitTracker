package com.example.user.habittracker

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log
import com.example.user.habittracker.Main2Activity
import org.jetbrains.anko.db.*
import java.util.*
import kotlin.collections.ArrayList

interface RowParser<T> {
    fun parseRow(columns: Array<Any>): T
}

class Labels(val Name : String)

class MySqlHelper1(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "mydb") {
    companion object {
        val tableName1 = "Labels"
        val Id1 = "_id"
        val Name1 = "name"

        val tableName2 = "Habits"
        val Id2 = "_id"
        val Label = "label"
        val Name2 = "name"
        val Freq = "freq"

        val tableName3 = "Statistics"
        val Id3 = "_id"
        val Date = "date"
        val HabitName = "habit_name"

        private var instance: MySqlHelper1? = null
        @Synchronized
        fun getInstance(ctx: Context): MySqlHelper1 {
            if (instance == null) {
                instance = MySqlHelper1(ctx)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(tableName1, true,
                Id1 to INTEGER + PRIMARY_KEY,
                Name1 to TEXT)

        db.createTable(tableName2, true,
                Id2 to INTEGER + PRIMARY_KEY,
                Label to TEXT,
                Name2 to TEXT,
                Freq to INTEGER)

        db.createTable(tableName3, true,
                Id3 to INTEGER + PRIMARY_KEY,
                Date to TEXT,
                HabitName to TEXT)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("MAIN", "Dropped!")
        db.dropTable(tableName1, true)
        db.dropTable(tableName2, true)
        db.dropTable(tableName3, true)

        onCreate(db)
    }

    fun AddLabel(ctx: Context,name: String){
        getInstance(ctx).use {
            insert(tableName1, Name1 to name)
        }
    }

    fun AddHabit(ctx: Context, label: String, name: String, freq: Int){
        getInstance(ctx).use {
            insert(tableName2, Label to label,
                    Name2 to name, Freq to freq)
        }
    }

    fun AddStatistics(ctx: Context, day: Int, month: Int, year: Int, habitName: String){
        getInstance(ctx).use {
            insert(tableName3, Date to day.toString() + month.toString() + year.toString(),
                    HabitName to habitName)
        }
    }

    fun SelectLabel(ctx: Context) : ArrayList<String>{

        var list = ArrayList<String>()

        val db = this.writableDatabase

        val cur = db.rawQuery("SELECT * FROM $tableName1",null)

        if (cur.moveToFirst()) {
            while(cur.moveToNext()){
                list.add(cur.getString(1))
            }

            cur.close()
        }

        return list
    }

    fun SelectHabit(ctx: Context , name: String) : ArrayList<String>{

        var list = ArrayList<String>()

        val db = this.writableDatabase

        val cur = db.rawQuery("SELECT * FROM $tableName2", null)

        if (cur.moveToFirst()) {
            while(cur.moveToNext()){
                list.add(cur.getString(3))
            }

            cur.close()
        }

        return list
    }

    fun SelectStatistis(ctx: Context, day: Int, month: Int, year: Int) : SelectQueryBuilder{
        val data = getInstance(ctx).use {
            select(tableName3).whereArgs("$Date = {date}",
                    Date to day.toString() + month.toString() + year.toString())
        }
        return data
    }

    fun SelectStatistis(ctx: Context, habitName: String) : SelectQueryBuilder{
        val data = getInstance(ctx).use {
            select(tableName3).whereArgs("$HabitName = {habitName}",
                    HabitName to habitName)
        }
        return data
    }

    fun SelectStatistis(ctx: Context, day: Int, month: Int, year: Int,
                          habitName: String) : SelectQueryBuilder{
        val data = getInstance(ctx).use {
            select(tableName3).whereArgs("($Date = {date}) and ($HabitName = {habitName})",
                    Date to day.toString() + month.toString() + year.toString(),
                    HabitName to habitName)
        }
        return data
    }

    fun DeleteLabel(ctx: Context, name: String) {
        getInstance(ctx).use {
            delete(tableName1, "",Name1 to name)
        }
    }

    fun DeleteHabit(ctx: Context, id: Int) {
        getInstance(ctx).use {
            delete(tableName2, "", Id2 to id)
        }
    }

    fun DeleteHabit(ctx: Context, name: String) {
        getInstance(ctx).use {
            delete(tableName2, "",Name2 to name)
        }
    }

    fun DeleteStatistics(ctx: Context, day: Int, month: Int, year: Int) {
        getInstance(ctx).use {
            delete(tableName3, "",Date to day.toString() + month.toString() + year.toString())
        }
    }

    fun removeEverything() {
        val db = this.writableDatabase
        db.dropTable(tableName1, true)
        db.dropTable(tableName2, true)
        db.dropTable(tableName3, true)
        db.close()
    }
}