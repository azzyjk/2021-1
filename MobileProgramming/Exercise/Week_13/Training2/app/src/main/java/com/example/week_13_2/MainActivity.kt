package com.example.week_13_2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.ProxyFileDescriptorCallback
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.example.week_13_2.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var googleMap: GoogleMap
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest:LocationRequest
    lateinit var locationCallback: LocationCallback

    var loc = LatLng(43.068301, 141.360578)
    var startUpdate = false

    override fun onResume() {
        super.onResume()
        if(!startUpdate) startLocationUpdate()
    }

    override fun onPause() {
        super.onPause()
        if(!startUpdate) stopLocationUpdate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSpinner()
        initMap()
    }

    private fun initLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create().apply{
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

         locationCallback = object: LocationCallback(){
             override fun onLocationResult(location: LocationResult) {
                 if(location.locations.size == 0) return
                 val curIdx = location.locations.size - 1
                 loc = LatLng(location.locations[curIdx].latitude, location.locations[curIdx].longitude)
                 setCurrentLocation(loc)
             }
         }
    }

    private fun setCurrentLocation(location: LatLng){
        val option = MarkerOptions()
        option.position(location)
        option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        googleMap.addMarker(option)
        googleMap.moveCamera((CameraUpdateFactory.newLatLngZoom(location, 16.0f)))
    }

    fun getLastLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
        else{
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                if(it != null){
                    loc = LatLng(it.latitude, it.longitude)
                    setCurrentLocation(loc)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) startLocationUpdate()
            else{
                Toast.makeText(this, "위치정보를 제공하셔야 합니다.", Toast.LENGTH_SHORT).show()
                setCurrentLocation(loc)
            }
        }
    }

    private fun stopLocationUpdate(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        startUpdate = false
    }

    private fun startLocationUpdate(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
        else{
            if(!checkLocationServicesStatus()) showLocationServicesSetting()
            else{
                startUpdate = true
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest, locationCallback, Looper.getMainLooper()
                )
            }
        }
    }

    private fun checkLocationServicesStatus():Boolean{
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
    }

    private fun showLocationServicesSetting(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage("앱을 사용하기 위해서는 위치서비스가 필요합니다.\n위치 설정을 허용하겠습니까?")

        builder.setPositiveButton("설정", {dialog, id->
            val GpsSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(GpsSettingIntent, 1000)
        })

        builder.setNegativeButton("취소", {dialog, id->
            dialog.dismiss()
            setCurrentLocation(loc)
        })

        builder.create().show()
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ArrayList<String>())

        adapter.add("Hybrid")
        adapter.add("Normal")
        adapter.add("Satellite")
        adapter.add("Terrian")

        binding.apply{
            spinner.adapter=adapter
            spinner.setSelection(1)
            spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
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
        initLocation()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            googleMap = it
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f))
            googleMap.setMinZoomPreference(10.0f)
            googleMap.setMaxZoomPreference(18.0f)

            val option = MarkerOptions()
            option.position(loc)
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            option.title("서울역")
            option.snippet("서울역 입니다")

            val mk1 = googleMap.addMarker(option)
            mk1.showInfoWindow()

        }
    }
}