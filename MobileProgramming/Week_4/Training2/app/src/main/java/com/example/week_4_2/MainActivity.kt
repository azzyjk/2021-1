package com.example.week_4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val emailText = findViewById<TextInputEditText>(R.id.emailText)
        emailText.addTextChangedListener {
            if(it.toString().contains('@')){
                textInputLayout.error =null
            }
            else{
                textInputLayout.error = "Not correct form!"
            }
        }
    }
}
