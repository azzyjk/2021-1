package com.example.a6_3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class InitActivity : AppCompatActivity() {
    val ADD_VOCA_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_VOCA_CODE){
            if(resultCode == Activity.RESULT_OK){
                val str = data?.getSerializableExtra("voc") as MyData
                Toast.makeText(this, "Add ${str.word}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init(){
        val vocaBtn = findViewById<Button>(R.id.vocaBtn)
        val addBtn = findViewById<Button>(R.id.addBtn)

        vocaBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        addBtn.setOnClickListener {
            val intent = Intent(this, AddVocaActivity::class.java)
            startActivityForResult(intent, ADD_VOCA_CODE)
        }
    }

}
