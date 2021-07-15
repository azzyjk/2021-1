package com.example.a4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    var countryArray = mutableListOf<String>("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
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
        val multiAuto = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        val editText = findViewById<EditText>(R.id.editText)
        val addBtn = findViewById<Button>(R.id.button)

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countryArray)

        multiAuto.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multiAuto.setAdapter(adapter)

        editText.addTextChangedListener {
            val str = it.toString()
            addBtn.isEnabled = str.isNotEmpty()
        } // jvm 1.8

//        editText.addTextChangedListener(object:TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                val str = s.toString()
//                addBtn.isEnabled = str.isNotEmpty()
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//        })

        addBtn.setOnClickListener {
            val text = editText.text.toString()
            adapter.add(text)
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }
    }
}
