package com.example.week_10_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    val url = "https://api.icndb.com/jokes/random"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getJoke()
    }

    private fun getJoke(){
        CoroutineScope(Dispatchers.IO).launch{
            val doc = Jsoup.connect(url).ignoreContentType(true).get()
            val json = JSONObject(doc.text())
            val joke = json.getJSONObject("value").getString("joke")
            Log.i("TEST", joke.toString())
        }

    }
}