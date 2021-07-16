package com.example.a201711425jeongjunwon

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class jjwMainActivity : AppCompatActivity() {
    lateinit var mainRecyclerView:RecyclerView
    var mainData:ArrayList<jjwData> = ArrayList()
    var TEST1_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jjw_main)
        initRecycler()
        init()
    }

    override fun onResume() {
        super.onResume()
        mainRecyclerView.adapter = jjwMainAdapter(mainData)
        val priceView = findViewById<TextView>(R.id.mainPriceView)
        var price = 0
        if(mainData.size > 0){
            for(i in mainData){
                price += i.price.toInt()
            }
        }
        priceView.text = price.toString()
    }

    private fun initRecycler() {
        mainRecyclerView = findViewById(R.id.mainRecyclerView)
        mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView.adapter = jjwMainAdapter(mainData)
    }

    private fun init(){
        val test1Btn = findViewById<Button>(R.id.test1Btn)
        val test2Btn = findViewById<Button>(R.id.test2Btn)
        val priceView = findViewById<TextView>(R.id.mainPriceView)
        var price = 0

        if(mainData.size > 0){
            for(i in mainData){
                price += i.price.toInt()
            }
        }
        priceView.text = price.toString()

        test1Btn.setOnClickListener {
            val intent = Intent(this, jjwTest1::class.java)
            startActivityForResult(intent, TEST1_REQUEST_CODE)
        }

        test2Btn.setOnClickListener {
            val intent = Intent(this, jjwTest2::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            TEST1_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK){
                    val str = data?.getSerializableExtra("menu") as jjwData
                    mainData.add(jjwData(str.name, str.price))

                    Log.i("TESTESTSET", str.toString())
                }
            }
        }
    }
}
