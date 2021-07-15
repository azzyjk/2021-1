package com.example.a3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        val group = findViewById<RadioGroup>(R.id.radioGroup)
        val image = findViewById<ImageView>(R.id.imageView)
        group.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radioButton -> image.setImageResource(R.drawable.welsh_1)
                R.id.radioButton2 -> image.setImageResource(R.drawable.welsh_2)
                R.id.radioButton3 -> image.setImageResource(R.drawable.welsh_3)
            }
        }
    }
}
