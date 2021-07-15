package com.example.a5_1

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

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MyAdapter(data)
    }

    private fun initData(){
        data.add(MyData("item1", 10))
        data.add(MyData("item2", 5))
        data.add(MyData("item3", 7))
        data.add(MyData("item4", 9))
        data.add(MyData("item5", 21))
        data.add(MyData("item6", 3))
        data.add(MyData("item7", 1))
        data.add(MyData("item8", 10))
    }

}
