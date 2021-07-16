package com.example.a201711425_jjw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class JJWMapFragment : Fragment() {
    lateinit var googleMap: GoogleMap
    val seoulStationLoc = LatLng(37.553873417105144, 126.97060462840896)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()
    }
    override fun onResume() {
        super.onResume()

    }

    private fun initMap(){
        val mapFr = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFr.getMapAsync {
            googleMap = it
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoulStationLoc, 16.0f))

            val option = MarkerOptions()
            option.position(seoulStationLoc)
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            option.title("서울역")
            googleMap.addMarker(option)
        }
    }


}