package com.example.a6_1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var mediaPlayer : MediaPlayer ?= null
    var vol = 0.5f
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onPause() {
        super.onPause()
        if(flag){
            mediaPlayer?.pause()
        }
    }



    override fun onResume() {
        super.onResume()
        if(flag){
            mediaPlayer?.start()
        }
    }

    private fun init(){
        val imageView = findViewById<VolumeControlView>(R.id.imageView)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val pauseBtn = findViewById<Button>(R.id.pauseBtn)
        val stopBtn = findViewById<Button>(R.id.stopBtn)

        imageView.setVolumeListener(object:VolumeControlView.VolumeListener{
            override fun onChanged(angle: Float) {
                vol = if(angle > 0) angle/360 else (360 + angle) / 360
                mediaPlayer?.setVolume(vol, vol)
            }

        })

        playBtn.setOnClickListener {
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this,R.raw.song)
            }
            mediaPlayer?.setVolume(vol, vol)
            mediaPlayer?.start()
            flag = true
        }

        pauseBtn.setOnClickListener {
            mediaPlayer?.pause()
        }

        stopBtn.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            flag = false
        }
    }
}
