package com.example.week_13_1

import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.week_13_1.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var googleMap:GoogleMap
    val seoulStationLatLng = LatLng(37.553873417105144, 126.97060462840896)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSpinner()
        initMap()
    }

    private fun initSpinner(){
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())

        adapter.add("Hybrid")
        adapter.add("Normal")
        adapter.add("Satellite")
        adapter.add("Terrian")

        binding.apply{
            spinner.adapter = adapter
            spinner.setSelection(1)
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                        1 -> googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                        2 -> googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                        3 -> googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        }
    }

    private fun initMap(){
        val mapFr = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFr.getMapAsync {
            googleMap = it
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoulStationLatLng, 16.0f))
            googleMap.setMinZoomPreference(10.0f)
            googleMap.setMaxZoomPreference(18.0f)

            val option = MarkerOptions()
            option.position(seoulStationLatLng)
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            option.title("서울역")
            option.snippet("서울역입니당")

            val mk1 = googleMap.addMarker(option)
            mk1.showInfoWindow()
        }
    }
}
//AIzaSyCrI0Feh5KvVf1BHxa7hbUi5YEE2NOAs5I