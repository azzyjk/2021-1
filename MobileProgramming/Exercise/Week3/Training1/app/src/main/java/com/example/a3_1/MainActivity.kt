package com.example.a3_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        val bmiBtn = findViewById<Button>(R.id.bmiBtn)
        val weightText = findViewById<EditText>(R.id.weightText)
        val heightText = findViewById<EditText>(R.id.heightText)
        val image = findViewById<ImageView>(R.id.imageView)

        bmiBtn.setOnClickListener {
            val weight = weightText.text.toString().toDouble()
            val height = heightText.text.toString().toDouble()
            val bmi = weight / (height/100).pow(2.0)
            when{
                bmi >= 35 ->{
                    image.setImageResource(R.drawable.ic_mood_bad_black_24dp)
                    Toast.makeText(this, "BMI 35 Upper!!", Toast.LENGTH_SHORT).show()
                }
                bmi >= 23 ->{
                    image.setImageResource(R.drawable.ic_mood_bad_black_24dp)
                    Toast.makeText(this, "BMI 23 Upper!!", Toast.LENGTH_SHORT).show()
                }
                bmi >= 18.5 ->{
                    image.setImageResource(R.drawable.ic_mood_black_24dp)
                    Toast.makeText(this, "BMI Normal:)!!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    image.setImageResource(R.drawable.ic_mood_black_24dp)
                    Toast.makeText(this, "BMI Less:(!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
