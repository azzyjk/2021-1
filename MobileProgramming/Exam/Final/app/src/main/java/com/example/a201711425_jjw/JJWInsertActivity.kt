package com.example.a201711425_jjw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a201711425_jjw.databinding.ActivityJJWInsertBinding

class JJWInsertActivity : AppCompatActivity() {
    lateinit var binding: ActivityJJWInsertBinding
    lateinit var myDBHelper: JJWMyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJJWInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val intent = getIntent()
        val title = intent.getStringExtra("title").toString()

        myDBHelper = JJWMyDBHelper(this)
        binding.apply {
            titleEdit.setText(title)
            contentEdit.setText(title + " City Hall")

            insertBtn.setOnClickListener {
                val lat = latEdit.text.toString().toDouble()
                val lon = lonEdit.text.toString().toDouble()
                val title = titleEdit.text.toString()
                val content = contentEdit.text.toString()
                myDBHelper.insertFavorite(JJWFavorite(0, lat, lon, title, content))
                finish()
            }
        }
    }
}
