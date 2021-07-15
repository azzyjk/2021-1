package com.example.a6_2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import java.security.Permission

class MainActivity : AppCompatActivity() {
    val CALL_REQUEST = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun callDigAction(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Need CALL_PHONE Permission")
            .setTitle("Permission Granted")
            .setPositiveButton("OK"){
                _, _ -> ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), CALL_REQUEST)
            }
        val dig = builder.create()
        dig.show()
    }

    private fun callAction(){
        val uri = Uri.parse("tel:010-1234-1234")
        val callIntent = Intent(Intent.ACTION_CALL, uri)
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){
            callDigAction()
        }
        else{
            startActivity(callIntent)
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
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Access", Toast.LENGTH_SHORT).show()
                    callAction()
                }
                else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init(){
        val msgBtn = findViewById<Button>(R.id.msgBtn)
        val callBtn = findViewById<Button>(R.id.callBtn)
        val mapBtn = findViewById<Button>(R.id.mapBtn)
        val webBtn = findViewById<Button>(R.id.webBtn)

        callBtn.setOnClickListener {
            callAction()
        }
        msgBtn.setOnClickListener {
            val uri = Uri.parse("sms:010-1234-1234")
            val msgIntent = Intent(Intent.ACTION_SENDTO, uri)
            msgIntent.putExtra("sms_body", "Help me")
            startActivity(msgIntent)
        }
        mapBtn.setOnClickListener {
            val uri = Uri.parse("geo:37.543684, 127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(mapIntent)
        }
        webBtn.setOnClickListener {
            val uri = Uri.parse("http://www.naver.com")
            val webIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(webIntent)
        }
    }
    // 전화
    // 지도
    // 메세지
    // site
}
