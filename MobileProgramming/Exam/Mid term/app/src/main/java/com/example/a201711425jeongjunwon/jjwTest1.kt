package com.example.a201711425jeongjunwon

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class jjwTest1 : AppCompatActivity() {
    lateinit var test1RecyclerView: RecyclerView
    lateinit var adapter: jjwAdapter
    var test1data:ArrayList<jjwData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jjw_test1)
        initData()
        initRecycler()
    }

    private fun initRecycler() {
        test1RecyclerView = findViewById(R.id.test1RecyclerView)
        test1RecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = jjwAdapter(test1data)
        adapter.itemClickListener = object:jjwAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: RecyclerView.ViewHolder,
                view: View,
                data: jjwData,
                position: Int
            ) {
                val intent = Intent()
                intent.putExtra("menu", jjwData(data.name, data.price))
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        }

        test1RecyclerView.adapter = adapter
    }

    private fun initData() {
        val scan = Scanner(resources.openRawResource(R.raw.jjw))
        while(scan.hasNextLine()){
            val name = scan.nextLine()
            val price = scan.nextLine()
            test1data.add(jjwData(name, price))
        }
        scan.close()
    }
}
