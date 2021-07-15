package com.example.week4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    var countries = mutableListOf(
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

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
        autoText.setAdapter(adapter)

        val multiAutoText = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        multiAutoText.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        multiAutoText.setAdapter(adapter)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        editText.addTextChangedListener {
            val str = it.toString()
            button.isEnabled = str.isNotEmpty()
        }
//        editText.addTextChangedListener(
//            afterTextChanged = {
//                val str = it.toString()
//                button.isEnabled = str.isNotEmpty()
//            }
//        )
        /*editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                button.isEnabled = str.isNotEmpty()
                // TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO("Not yet implemented")
            }

        })*/
        button.setOnClickListener {
            adapter.add(editText.text.toString())
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }
    }
}
