package com.example.a201711425jeongjunwon

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

class jjwTest2 : AppCompatActivity() {
    val CALL_REQUEST = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jjw_test2)
        init()
    }

    fun callAlertDig(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("반드시 CALL_PHONE 권한이 허용되어야 합니다.")
            .setTitle("권한허용")
            .setPositiveButton("OK"){
                    _, _ -> ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), CALL_REQUEST)
            }
        val dig = builder.create()
        dig.show()
    }

    fun callAction(){
        val number = Uri.parse("tel:010-1234-1234")
        val callIntent = Intent(Intent.ACTION_CALL, number)
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            callAlertDig()
        }
        else
            startActivity(callIntent)
    }

    private fun init(){
        val numberView = findViewById<TextView>(R.id.numberView)

        numberView.setOnClickListener {
            callAction()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CALL_REQUEST->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    callAction()
                }
            }
        }
    }
}
