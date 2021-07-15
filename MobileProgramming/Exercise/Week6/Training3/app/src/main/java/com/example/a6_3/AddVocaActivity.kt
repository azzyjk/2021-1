package com.example.a6_3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.row.*
import org.w3c.dom.Text
import java.io.PrintStream

class AddVocaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_voca)
        init()
    }

    private fun init(){
        val vocaAddBtn = findViewById<Button>(R.id.vocaAddBtn)
        val cancelBtn = findViewById<Button>(R.id.cancleBtn)
        val wordText = findViewById<EditText>(R.id.wordText)
        val meanText = findViewById<EditText>(R.id.meanText)

        cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        vocaAddBtn.setOnClickListener {
            writeFile(wordText.text.toString(), meanText.text.toString())
        }


    }
    fun writeFile(word : String, mean : String){
        val output = PrintStream(openFileOutput("out.txt", Context.MODE_APPEND))
        output.println(word)
        output.println(mean)
        output.close()
        val intent = Intent()
        intent.putExtra("voc", MyData(word, mean))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
