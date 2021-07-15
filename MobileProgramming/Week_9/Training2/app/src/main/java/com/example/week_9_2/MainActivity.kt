package com.example.week_9_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week_9_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val imgFragment = ImageFragment()
    val itemFragment = ItemFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        val fragment = supportFragmentManager.beginTransaction()
//        fragment.addToBackStack(null)
        
        fragment.replace(R.id.frameLayout, imgFragment)
        fragment.commit()
        binding.apply{
            button.setOnClickListener {
                if(!imgFragment.isVisible){
                    val fragment = supportFragmentManager.beginTransaction()
                    fragment.addToBackStack(null)
                    fragment.replace(R.id.frameLayout, imgFragment)
                    fragment.commit()
                }
            }
            button2.setOnClickListener {
                if(!itemFragment.isVisible){
                    val fragment = supportFragmentManager.beginTransaction()
                    fragment.addToBackStack(null)
                    fragment.replace(R.id.frameLayout, itemFragment)
                    fragment.commit()
                }
            }

        }
    }
}
