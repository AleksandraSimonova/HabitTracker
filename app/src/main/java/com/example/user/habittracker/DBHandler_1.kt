package com.example.user.habittracker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.user.habittracker.Main2Activity
import org.jetbrains.anko.db.*

class MySqlHelper1(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "mydb") {
    companion object {
        val tableName = "Habit"
        val colId = "_id"
        val colLabel = "label"
        val colName = "name"
        val colFreq = "freq"
        private var instance: MySqlHelper1? = null
        @Synchronized
        fun getInstance(ctx: Context): MySqlHelper1 {
            if (instance == null) {
                instance = MySqlHelper1(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(tableName, true,
                colId to INTEGER + PRIMARY_KEY,
                colLabel to TEXT,
                colName to TEXT,
                colFreq to INTEGER)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun insert(ctx: Context, label: String, name: String, freq: Int){
        getInstance(ctx).use {
            insert(tableName, colLabel to label, colName to name, colFreq to freq)
        }
    }

    fun select(ctx: Context, name: String) : SelectQueryBuilder{
        val data = getInstance(ctx).use {
            select(tableName).whereArgs("$colName = {name}",
                    colName to name)
        }
        return data
    }

    fun delete(ctx: Context, id: Int) {
        getInstance(ctx).use {
            delete(tableName, "", colId to id)
        }
    }

    fun delete(ctx: Context, name: String) {
        getInstance(ctx).use {
            delete(tableName, "",colName to name)
        }
    }
}