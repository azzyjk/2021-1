package com.example.a4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        val textLayout = findViewById<TextInputLayout>(R.id.textInputLayout9)
        val emailText = findViewById<TextInputEditText>(R.id.emailText)

//        emailText.addTextChangedListener {
//            if(it.toString().contains("@")){
//                textLayout.error = ""
//            }
//            else{
//                textLayout.error = "Not correct email form"
//            }
//        }
        emailText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(emailText.text.toString().contains('@')){
                    textLayout.error = ""
                }
                else{
                    textLayout.error = "Not correct email type"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

    }
}
