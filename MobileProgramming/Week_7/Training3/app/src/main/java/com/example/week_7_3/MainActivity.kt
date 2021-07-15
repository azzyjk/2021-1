package com.example.week_7_3

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.example.week_7_3.databinding.ActivityMainBinding
import com.example.week_7_3.databinding.MypickerdlgBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var mymemo = ""
    var myhour = 0
    var mymin = 0
    var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val str = intent.getStringExtra("time")
        if(str!=null)
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        init()
    }
    private fun init(){
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dlgBinding = MypickerdlgBinding.inflate(layoutInflater)
            val dlgBuilder = AlertDialog.Builder(this)
            dlgBuilder.setView(dlgBinding.root)
                .setPositiveButton("ADD"){
                    _, _ ->
                    mymemo = dlgBinding.editText.text.toString()
                    myhour = dlgBinding.timePicker.hour
                    mymin = dlgBinding.timePicker.minute
                    message = myhour.toString() + " hour "+mymin.toString()+" minute : " + mymemo
                    val timerTask = object : TimerTask(){
                        override fun run() {
                            makeNotification()
                        }
                    }
                    val timer = Timer()
                    timer.schedule(timerTask, 2000)
                    Toast.makeText(this, "Alram Add!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel"){
                    _,_->

                }
                .show()
//            val dlg = dlgBuilder.create()
//            dlg.show()
        }
    }
    fun makeNotification(){
        val id = "MyChannel"
        val name = "TimeCheckChannel"
        val notificationChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableVibration(true)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.BLUE
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        val builder = NotificationCompat.Builder(this, id)
            .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
            .setContentTitle("Alarm Notification")
            .setContentText(message)
            .setAutoCancel(true)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("time", message)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notificationChannel)
        val notification = builder.build()

        manager.notify(10, notification)

    }
}
