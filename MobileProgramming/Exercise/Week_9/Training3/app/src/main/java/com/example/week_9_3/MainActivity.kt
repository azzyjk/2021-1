package com.example.week_9_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week_9_3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val textArr = arrayListOf("Image", "List")
    val iconArr = arrayListOf(R.drawable.ic_baseline_image_24, R.drawable.ic_baseline_list_24)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.viewPager.adapter = MyFragmentStateAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, position ->
            tab.text = textArr[position]
            tab.setIcon(iconArr[position])
        }.attach()
    }
}