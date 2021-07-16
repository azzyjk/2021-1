package com.example.a201711425_jjw

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class JJWMyDBHelper(val context: Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_NAME = "mydb.db"
        val DB_VERSION = 1
        val TABLE_NAME = "mylocation"
        val PID = "id"
        val PLAT = "lat"
        val PLON = "lng"
        val PTITLE ="title"
        val PCONTENT = "content"


    }

    fun findFavorite(title:String) : JJWFavorite{
        val strsql = "select * from $TABLE_NAME where $PTITLE='$title';"
        val db =readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val flag = cursor.count != 0
        val favorite = showRecord(cursor)
        cursor.close()
        db.close()
        return favorite
    }

    fun getAllRecord():JJWFavorite{
        val strsql = "select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val favorite = showRecord(cursor)
        cursor.close()
        db.close()

        return favorite
    }

    private fun showRecord(cursor: Cursor):JJWFavorite{
        cursor.moveToFirst()
        val attrcount = cursor.columnCount
        var JJWFavorite:JJWFavorite = JJWFavorite(0, 0.0, 0.0, "", "")

        if(cursor.count == 0) return JJWFavorite


//            var pId:Int = cursor.getString(0).toInt()
//            var pLat: Double = cursor.getString(1).toDouble()
//            var pLon: Double = cursor.getString(2).toDouble()
//            var pTitle: String = cursor.getString(3)
//            var pContent: String = cursor.getString(4)
        JJWFavorite.pId = cursor.getString(0).toInt()
        JJWFavorite.pLat = cursor.getString(1).toDouble()
        JJWFavorite.pLon = cursor.getString(2).toDouble()
        JJWFavorite.pTitle = cursor.getString(3)
        JJWFavorite.pContent = cursor.getString(4)
        Log.i("TEST", JJWFavorite.toString())
//            cafeList.add(Favorite(pId, pName, pLat, pLon))


        return JJWFavorite
    }
    fun insertFavorite(JJWFavorite: JJWFavorite):Boolean{
        val values = ContentValues()
//        values.put(PNAME, cafe.pName)
        values.put(PLAT, JJWFavorite.pLat)
        values.put(PLON, JJWFavorite.pLon)
        values.put(PTITLE, JJWFavorite.pTitle)
        values.put(PCONTENT, JJWFavorite.pContent)

        val db = writableDatabase

        val flag = db.insert(TABLE_NAME, null, values) > 0 // 성공시 1 실패 0
        db.close()
        return flag
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table =
            "create table if not exists $TABLE_NAME(" +
                    "$PID integer primary key autoincrement, "+
                    "$PLAT double, " +
                    "$PLON double, " +
                    "$PTITLE text, " +
                    "$PCONTENT text);"
        Log.i("TEST", create_table)
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }
}