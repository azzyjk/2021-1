package com.example.final_exercise

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView

class MyDBHelper(val context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_NAME = "mydb.db"
        val DB_VERSION = 1
        val TABLE_NAME = "cafes"
        val PID = "pid"
        val PNAME = "pname"
        val PLAT = "plat"
        val PLON = "plon"
    }

    fun getAllRecord():ArrayList<CafeInfo>{
        val strsql = "select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val cafeList = showRecord(cursor)
        cursor.close()
        db.close()

        return cafeList
    }
    private fun showRecord(cursor: Cursor):ArrayList<CafeInfo>{
        cursor.moveToFirst()
        val attrcount = cursor.columnCount
        val cafeList = ArrayList<CafeInfo>()

        if(cursor.count == 0) return arrayListOf()

        do{
            var pId:Int = cursor.getString(0).toInt()
            var pName:String = cursor.getString(1)
            var pLat: Double = cursor.getString(2).toDouble()
            var pLon: Double = cursor.getString(3).toDouble()
            cafeList.add(CafeInfo(pId, pName, pLat, pLon))

        }while(cursor.moveToNext())
        return cafeList
    }
    fun insertCafe(cafe: CafeInfo):Boolean{
        val values = ContentValues()
        values.put(PNAME, cafe.pName)
        values.put(PLAT, cafe.pLat)
        values.put(PLON, cafe.pLon)

        val db = writableDatabase

        val flag = db.insert(TABLE_NAME, null, values) > 0 // 성공시 1 실패 0
        db.close()
        return flag
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table =
            "create table if not exists $TABLE_NAME(" +
                    "$PID integer primary key autoincrement, "+
                    "$PNAME text, " +
                    "$PLAT double, " +
                    "$PLON);"
        Log.i("TEST", create_table)
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }
}