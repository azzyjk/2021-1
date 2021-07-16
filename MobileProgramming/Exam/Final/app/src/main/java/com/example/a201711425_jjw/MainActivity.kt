package com.example.a201711425_jjw

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.a201711425_jjw.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var googleMap:GoogleMap
    lateinit var myDBHelper: JJWMyDBHelper


    val seoulStationLoc = LatLng(37.553873417105144, 126.97060462840896)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMap()
        initDB()
        init()
        initPermission()
        if(intent.hasExtra("title")){
            Log.i("TEST", "HERE")
            val title = intent.getStringExtra("title").toString()
            val result = myDBHelper.findFavorite(title)
            if(result.pTitle==""){
                Log.i("TEST", "NOT EXIST")
                val intent = Intent(this@MainActivity, JJWInsertActivity::class.java)
                intent.putExtra("title", title)
                startActivity(intent)
            }
            else{
                setLocation(result)
                Log.i("TEST", "EXIST")
            }
//                Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"문자 수신 동의함", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 100)
        }
    }

    private fun init(){
        myDBHelper = JJWMyDBHelper(this)

        binding.apply{
            findBtn.setOnClickListener {
                val title = findEdit.text.toString()
                val result:JJWFavorite = myDBHelper.findFavorite(title)
                if(result.pTitle==""){
                    Log.i("TEST", "NOT EXIST")
                    val intent = Intent(this@MainActivity, JJWInsertActivity::class.java)
                    intent.putExtra("title", title)
                    startActivity(intent)
                }
                else{
                    setLocation(result)
                    Log.i("TEST", "EXIST")
                }
            }
        }
    }

    private fun initDB(){
        val dbfile = getDatabasePath("mydb.db")
        if(!dbfile.parentFile.exists()){
            dbfile.parentFile.mkdir()
        }
        if(!dbfile.exists()){
            Log.i("TEST", "HELLO")
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


    private fun initMap(){
        val mapFr = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFr.getMapAsync {
            googleMap = it
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoulStationLoc, 16.0f))
            googleMap.setMinZoomPreference(10.0f)
            googleMap.setMaxZoomPreference(18.0f)

            val option = MarkerOptions()
            option.position(seoulStationLoc)
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            option.title("서울역")
            option.snippet("201711425 정준원")

            val mk1 = googleMap.addMarker(option)
            mk1.showInfoWindow()
        }
    }

    private fun setLocation(favorite:JJWFavorite){
        val mapFr = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFr.getMapAsync {
            googleMap = it
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(favorite.pLat, favorite.pLon), 16.0f))
            googleMap.setMinZoomPreference(10.0f)
            googleMap.setMaxZoomPreference(18.0f)

            val option = MarkerOptions()
            option.position(LatLng(favorite.pLat, favorite.pLon))
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            option.title(favorite.pTitle)
            option.snippet(favorite.pContent)

            val mk1 = googleMap.addMarker(option)
            mk1.showInfoWindow()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "문자 수신 동의함", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "문자 수신 동의 필요함", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent!=null){
            if(intent.hasExtra("title")){
                Log.i("TEST", "HERE")
                val title = intent.getStringExtra("title").toString()
                val result = myDBHelper.findFavorite(title)
                if(result.pTitle==""){
                    Log.i("TEST", "NOT EXIST")
                    val intent = Intent(this@MainActivity, JJWInsertActivity::class.java)
                    intent.putExtra("title", title)
                    startActivity(intent)
                }
                else{
                    setLocation(result)
                    Log.i("TEST", "EXIST")
                }
//                Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
            }
        }
    }

}