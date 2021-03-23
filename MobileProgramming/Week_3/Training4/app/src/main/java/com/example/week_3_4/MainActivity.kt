package com.example.week_3_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView

class MainActivity : AppCompatActivity() {
    var countries = arrayOf(
        "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
        "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
        "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
        "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium"
    )

    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        val autoText = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val countries2 = resources.getStringArray(R.array.countries_array)

//        resources.getStringArray()
//        <resources>
//        <string-array name="countries_array">
//        <item>Afghanistan</item>
//        <item>Albania</item>
//        <item>Algeria</item>
//        <item>American Samoa</item>
//        <item>Andorra</item>
//        <item>Bahrain</item>
//        <item>Bangladesh</item>
//        <item>Barbados</item>
//        <item>Belarus</item>
//        <item>Belgium</item>
//        </string-array>
//        </resources>

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries2)
        autoText.setAdapter(adapter)

        val multiAutoText = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        multiAutoText.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multiAutoText.setAdapter(adapter)
    }
}
