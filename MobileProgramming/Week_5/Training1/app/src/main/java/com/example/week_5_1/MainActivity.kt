package com.example.week_5_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        data.add(MyData("item1", 10))
        data.add(MyData("item2", 20))
        data.add(MyData("item3", 15))
        data.add(MyData("item4", 13))
        data.add(MyData("item5", 11))
        data.add(MyData("item6", 7))
        data.add(MyData("item7", 8))
        data.add(MyData("item8", 14))
        data.add(MyData("item9", 10))
        data.add(MyData("item10", 15))
        data.add(MyData("item11", 10))
    }
}
