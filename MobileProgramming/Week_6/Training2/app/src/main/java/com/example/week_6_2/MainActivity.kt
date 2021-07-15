package com.example.week_6_2

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.week_6_2.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val CALL_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    fun callAlertDig(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Must need CALL_PHONE permission")
            .setTitle("Permission Granted")
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CALL_REQUEST->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show()
                    callAction()
                }
                else{
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init(){
        with(binding){
            callBtn.setOnClickListener {
                callAction()
            }

            msgBtn.setOnClickListener {
                val message = Uri.parse("sms:010-1234-1234")
                val messageIntent = Intent(Intent.ACTION_SENDTO, message)
                messageIntent.putExtra("sms_body", "Help.....")
                startActivity(messageIntent)
            }

            webBtn.setOnClickListener {
                val site = Uri.parse("http://www.naver.com")
                val webIntent = Intent(Intent.ACTION_VIEW, site)
                startActivity(webIntent)
            }

            mapBtn.setOnClickListener {
                val loc = Uri.parse("geo:37.543684,127.077130?z=16")
                val mapIntent = Intent(Intent.ACTION_VIEW, loc)
                startActivity(mapIntent)
            }
        }
    }
}
