package com.example.week_11_1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView

class MyDBHelper(val context: Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_NAME = "mydb.db"
        val DB_VERSION = 1
        val TABLE_NAME = "products"
        val PID = "pid"
        val PNAME = "pname"
        val PQUANTITY = "pquantity"
    }

    fun insertProduct(product: Product):Boolean{
        val values = ContentValues()
        values.put(PNAME, product.pName)
        values.put(PQUANTITY, product.pQuantity)

        val db = writableDatabase

        val flag = db.insert(TABLE_NAME, null, values) > 0 // 성공시 1 실패 0
        db.close()
        return flag
    }

    // select * from product where name = 'pid';
    fun findProduct(name:String):Boolean{
        val strsql = "select * from $TABLE_NAME where $PNAME='$name';"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val flag = cursor.count != 0
        showRecord(cursor)
        cursor.close()
        db.close()
        return flag
    }

    fun findProduct2(name: String):Boolean{
        val strsql = "select * from $TABLE_NAME where $PNAME like '$name%';"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val flag = cursor.count != 0
        showRecord(cursor)
        cursor.close()
        db.close()
        return flag
    }

    // select * from product where pid = 'pid';
    fun deleteProduct(pid:String):Boolean{
        val strsql = "select * from $TABLE_NAME where $PID = '$pid';"
        val db = writableDatabase
        val cursor = db.rawQuery(strsql, null)
        val flag = cursor.count != 0
        if(flag){
            cursor.moveToFirst()
            db.delete(TABLE_NAME, "$PID=?", arrayOf(pid))
        }
        cursor.close()
        db.close()
        return flag
    }

    fun updateProduct(product:Product):Boolean{
        val pid = product.pId
        val strsql = "select * from $TABLE_NAME where $PID='$pid';"
        val db = writableDatabase
        val cursor = db.rawQuery(strsql, null)
        val flag = cursor.count !=0
        if(flag){
            cursor.moveToFirst()
            val values = ContentValues()
            values.put(PNAME, product.pName)
            values.put(PQUANTITY, product.pQuantity)
            db.update(TABLE_NAME, values, "$PID=?", arrayOf(pid.toString()))
        }
        cursor.close()
        db.close()
        return flag
    }

    fun getAllRecord(){
        val strsql = "select * from $TABLE_NAME;"
        val db = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        showRecord(cursor)
        cursor.close()
        db.close()
    }

    private fun showRecord(cursor: Cursor){
        cursor.moveToFirst()
        val attrcount = cursor.columnCount
        val activity = context as MainActivity
        activity.binding.tableLayout.removeAllViewsInLayout()

        //타이틀 만들기
        val tableRow = TableRow(activity)
        val rowParam = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        tableRow.layoutParams = rowParam

        val viewParam = TableRow.LayoutParams(0, 100, 1f)

        for(i in 0 until attrcount){
            val textView = TextView(activity)
            textView.apply{
                layoutParams = viewParam
                text = cursor.getColumnName(i)
                setBackgroundColor(Color.LTGRAY)
                textSize = 15.0f
                gravity = Gravity.CENTER
            }
            tableRow.addView(textView)
        }
        activity.binding.tableLayout.addView(tableRow)
        if(cursor.count == 0) return

        //레코드 추가하기
        do{
            val row = TableRow(activity)
            row.apply{
                layoutParams = rowParam
                setOnClickListener {
                    for(i in 0 until attrcount){
                        val textView = row.getChildAt(i) as TextView
                        when(textView.tag){
                            0->activity.binding.pIdEdit.setText(textView.text)
                            1->activity.binding.pNameEdit.setText(textView.text)
                            2->activity.binding.pQuantityEdit.setText(textView.text)
                        }
                    }
                }
            }

            for(i in 0 until attrcount){
                val textView = TextView(activity)
                textView.apply{
                    tag = i
                    layoutParams = viewParam
                    text = cursor.getString(i)
                    textSize = 13.0f
                    gravity = Gravity.CENTER
                }
                row.addView(textView)
            }
            activity.binding.tableLayout.addView(row)
        }while(cursor.moveToNext())
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table =
                    "create table if not exists $TABLE_NAME(" +
                    "$PID integer primary key autoincrement, "+
                    "$PNAME text, " +
                    "$PQUANTITY integer);"
        Log.i("TEST", create_table)
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }
}