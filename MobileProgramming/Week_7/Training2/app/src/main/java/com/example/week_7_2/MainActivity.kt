package com.example.week_7_2

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    val APPLIST_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view:View){
        btnAction()
    }

    private fun btnAction() {
        val intent = Intent("com.example.myapplist")
        if(ActivityCompat.checkSelfPermission(this, "com.example.applists")
            !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf("com.example.applists"), APPLIST_REQUEST)
        }
        else{
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == APPLIST_REQUEST){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                btnAction()
            }
            else{
                finish()
            }
        }
    }
}
