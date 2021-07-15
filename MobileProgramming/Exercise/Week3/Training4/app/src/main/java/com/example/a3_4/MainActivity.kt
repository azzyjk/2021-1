package com.example.a3_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val countryArray = arrayOf("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
        "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
        "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
        "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium")
    lateinit var adapter:ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        val autoText = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val multiAutoText = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        val countries2 = resources.getStringArray(R.array.countries2)

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countryArray)
        val adapter2 = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries2)

        autoText.setAdapter(adapter)
        
        multiAutoText.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multiAutoText.setAdapter(adapter2)

        autoText.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Selected item : ${item}", Toast.LENGTH_SHORT).show()
        }
    }
}
