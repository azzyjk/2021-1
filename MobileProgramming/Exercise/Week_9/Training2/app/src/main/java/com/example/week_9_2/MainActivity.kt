package com.example.week_9_2

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week_9_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val imgFragment = ImageFragment()
    val listFragment = ItemFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.apply{
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, imgFragment)
            fragmentTransaction.commit()

            imgBtn.setOnClickListener {
                if(!imgFragment.isVisible){
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.frameLayout, imgFragment)
                    fragmentTransaction.commit()
                }
            }
            listBtn.setOnClickListener {
                if(!listFragment.isVisible){
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.frameLayout, listFragment)
                    fragmentTransaction.commit()
                }
            }
        }
    }
}