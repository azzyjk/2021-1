package com.example.a5_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter: MyAdapter
    var data :ArrayList<MyData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTTS()
        initData()
        initRecyclerView()
    }

    private fun initTTS() {

    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(data)
        adapter.listener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: RecyclerView.ViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                Toast.makeText(applicationContext, data.mean, Toast.LENGTH_SHORT).show()
            }

        }
        recyclerView.adapter = adapter
    }

    private fun initData(){
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val mean = scan.nextLine()
            data.add(MyData(word, mean, "GONE"))
        }
        scan.close()
    }
}
