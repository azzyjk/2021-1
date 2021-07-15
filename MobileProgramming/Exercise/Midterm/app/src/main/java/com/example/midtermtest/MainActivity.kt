package com.example.midtermtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var data:ArrayList<MyData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MyAdapter(data)
    }

    private fun initData() {
        val scan = Scanner(resources.openRawResource(R.raw.testdata))
        while(scan.hasNextLine()){
            val rawStr = scan.nextLine()
            val strArr = rawStr.split(", ")
            Log.i("TESTESTTEST", strArr[0].toString())
            Log.i("TESTESTTEST", strArr[1].toString())
            Log.i("TESTESTTEST", strArr[2].toString())
            Log.i("TESTESTTEST", strArr[3].toString())
            Log.i("TESTESTTEST", strArr[4].toString())
            data.add(MyData(strArr[0], strArr[1], strArr[2].toInt(), strArr[3], strArr[4]))

        }
    }
}
