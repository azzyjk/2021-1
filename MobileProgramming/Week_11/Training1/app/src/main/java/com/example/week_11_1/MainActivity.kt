package com.example.week_11_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.week_11_1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myDBHelper: MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         ()
        init()
        getAllRecord()
    }

    private fun initDB() {
        val dbfile = getDatabasePath("mydb.db")
        if(!dbfile.parentFile.exists()){
            dbfile.parentFile.mkdir()
        }
        if(!dbfile.exists()){
            val file = resources.openRawResource(R.raw.mydb)
            val fileSize = file.available()
            val buffer = ByteArray(fileSize)
            file.read(buffer)
            file.close()
            dbfile.createNewFile()
            val output = FileOutputStream(dbfile)
            output.write(buffer)
            output.close()
        }
    }

    fun getAllRecord(){

        myDBHelper.getAllRecord()
    }

    fun clearEditText(){
        binding.apply{
            pIdEdit.text.clear()
            pNameEdit.text.clear()
            pQuantityEdit.text.clear()
        }
    }


    private fun init(){
        myDBHelper = MyDBHelper(this)
        binding.apply{
            testsql.addTextChangedListener{
                val pname = it.toString()
                val result = myDBHelper.findProduct2(pname)

            }
            insertBtn.setOnClickListener {
                val name = pNameEdit.text.toString()
                val quantity = pQuantityEdit.text.toString().toInt()
                val product = Product(0, name, quantity)
                val result = myDBHelper.insertProduct(product)
                if(result){
                    getAllRecord()
                    Toast.makeText(this@MainActivity, "Data INSERT SUCCESS", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "Data INSERT Failed", Toast.LENGTH_SHORT).show()
                }
                clearEditText()
            }
            findBtn.setOnClickListener {
                val name = pNameEdit.text.toString()
                val result = myDBHelper.findProduct(name)
                if(result){
                    Toast.makeText(this@MainActivity, "RECORD FOUND", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "NO MATCH FOUND", Toast.LENGTH_SHORT).show()
                }
            }
            deleteBtn.setOnClickListener {
                val pid = pIdEdit.text.toString()
                val result = myDBHelper.deleteProduct(pid)
                if(result){

                    Toast.makeText(this@MainActivity, "DATA DELETE SUCCESS", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "DATA DELETE FAILED", Toast.LENGTH_SHORT).show()
                }
                getAllRecord()
                clearEditText()
            }
            updateBtn.setOnClickListener {
                val pid = pIdEdit.text.toString().toInt()
                val name = pNameEdit.text.toString()
                val quantity = pQuantityEdit.text.toString().toInt()
                val product = Product(pid, name, quantity)
                val result = myDBHelper.updateProduct(product)
                if(result){
                    getAllRecord()
                    Toast.makeText(this@MainActivity, "Data UPDATE SUCCESS", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "Data UPDATE Failed", Toast.LENGTH_SHORT).show()
                }
                clearEditText()
            }
        }
    }
}
