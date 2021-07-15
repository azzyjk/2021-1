package com.example.week_9_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week_9_3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val textarr = arrayListOf<String>("Image", "List")
    val iconarr = arrayListOf<Int>(R.drawable.ic_android_black_24dp, R.drawable.ic_content_paste_black_24dp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.viewPager.adapter = MyFragStateAdapter(this)
        TabLayoutMediator(binding.tablayout, binding.viewPager){
            tab, position ->
            tab.text = textarr[position]
            tab.setIcon(iconarr[position])
        }.attach()
    }
}
