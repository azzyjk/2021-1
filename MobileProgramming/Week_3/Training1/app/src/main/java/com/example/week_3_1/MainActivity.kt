package com.example.week_3_1

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

    fun init(){
        val bmiBtn = findViewById<Button>(R.id.bmiButton)
        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        val imageView = findViewById<ImageView>(R.id.imageView)
        bmiBtn.setOnClickListener {
            val bmi = weight.text.toString().toDouble() / (height.text.toString().toDouble() /100).pow(2.0)
            var resultString:String=""

            when{
                bmi >= 35 ->{
                    resultString = "Severe obesity"
                    imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
                }
                bmi >= 23 ->{
                    resultString = "Overweight"
                    imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                }
                bmi >= 18.5 ->{
                    resultString = "Normal"
                    imageView.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
                }
                else ->{
                    resultString = "Underweight"
                    imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                }
            }
            Toast.makeText(this, resultString, Toast.LENGTH_SHORT).show()
        }
    }
}
