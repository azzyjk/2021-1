package com.example.final_exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.final_exercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var myDBHelper: MyDBHelper
    lateinit var adapter:MyItemRecyclerViewAdapter

    val myViewModel:MyViewModel by viewModels()
    var cafeList = ArrayList<CafeInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
//        insertData()
        getAllData()

        for(i in cafeList){
            Log.i("TEST", i.toString())
            myViewModel.addCafe(i)
        }

//        Log.i("TEST", myViewModel.cafes.value.toString())
        for(i in myViewModel.cafes.value!!){
            Log.i("TEST", i.toString())
        }

    }

    private fun insertData() {
        myDBHelper.insertCafe(CafeInfo(0, "건국대학교", 37.54096665014146, 127.07886000096191))
        myDBHelper.insertCafe(CafeInfo(0, "세종대학교", 37.55002331899406, 127.0748733651723))
        myDBHelper.insertCafe(CafeInfo(0, "한양대학교", 37.556720652521314, 127.04626307426676))
        myDBHelper.insertCafe(CafeInfo(0, "동국대학교", 37.55697542492422, 127.00040025946653))
    }

    private fun getAllData(){
        cafeList = myDBHelper.getAllRecord()
    }

    private fun init(){
        myDBHelper = MyDBHelper(this)
        binding.apply{
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, ItemFragment())
            fragmentTransaction.commit()
        }
    }

}