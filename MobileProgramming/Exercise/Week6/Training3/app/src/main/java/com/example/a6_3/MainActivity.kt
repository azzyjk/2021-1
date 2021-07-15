package com.example.a6_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    var data:ArrayList<MyData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initRecyclerView()

    }

    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MyAdapter(data)
    }

    private fun fileRead(scan : Scanner){
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val mean = scan.nextLine()
            data.add(MyData(word, mean))
        }
        scan.close()
    }

    private fun initData(){
        val scan = Scanner(resources.openRawResource(R.raw.words))
        try{
            val scan2 = Scanner(openFileInput("out.txt"))
            fileRead(scan2)
        }catch(e:Exception){
            Toast.makeText(this,"No out.txt file", Toast.LENGTH_SHORT).show()
        }

        fileRead(scan)


    }
}
