package com.example.week_6_3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.PrintStream

class AddVocaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_voca)
        init()
    }

    private fun init(){
        val addBtn = findViewById<Button>(R.id.button3)
        val cancelBtn = findViewById<Button>(R.id.button4)
        val editWord = findViewById<EditText>(R.id.word)
        val editMeaning = findViewById<EditText>(R.id.meaning)

        addBtn.setOnClickListener {
            val word = editWord.text.toString()
            val meaning = editMeaning.text.toString()
            writeFile(word, meaning)
        }

        cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun writeFile(word:String, meaning:String){
        val output = PrintStream(openFileOutput("out.txt", Context.MODE_APPEND))
        output.println(word)
        output.println(meaning)
        output.close()
        val intent  = Intent()
        intent.putExtra("voc", MyData(word, meaning, "GONE"))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
