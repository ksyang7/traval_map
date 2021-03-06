package com.example.map

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker
        // 마커 아이콘 만들기
        val discriptor = getDiscriptorFromDrawable(R.drawable.marker)

        val center = LatLng(37.626449, 128.812631)
        val st1 = LatLng(37.6928511,128.5017604)
        val st2 = LatLng(37.7787561,128.8761327)
        val st3 = LatLng(37.8081794,128.9003072)
        val st4 = LatLng(37.5536119,129.1158937)



        val marker1 = MarkerOptions()
                .position(st1)
                .title("방아다리약수터 ")
                .icon(discriptor)

        mMap.addMarker(marker1)

        val marker2 = MarkerOptions()
            .position(st2)
            .title("오죽헌")
            .icon(discriptor)

        mMap.addMarker(marker2)

        val marker3 = MarkerOptions()
            .position(st3)
            .title("경포비스타호텔 ")
            .icon(discriptor)

        mMap.addMarker(marker3)

        val marker4 = MarkerOptions()
            .position(st4)
            .title("묵호항 ")
            .icon(discriptor)

        mMap.addMarker(marker4)


        // 카메라의 위치
        val cameraOption = CameraPosition.builder()
                .target(center)
                .zoom(9.5f)
                .build()

        var camera = CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)


    }
/*
    // 맵 클릭 리스터 - 맵 클릭하면 카트뷰 띄움
    googleMap!!.setOnMarkerClickListener (object : GoogleMap.OnMarkerClickListener {
        override fun onMarkerClick(marker: Marker): Boolean {
            card_view.visibility = View.VISIBLE
            return false
        }
    })
    // 맵 클릭 리스터 - 맵 클릭하면 카트뷰 없어짐
    googleMap!!.setOnMapClickListener (object :GoogleMap.OnMarkerClickListener{
        override  fun onMapClick(latLng: LatLng){
            card_view.visiblility = View.GONE
        }
    })

*/
fun onMarkerClick(marker: Marker) : Boolean{
        val clickCount = marker.tag as? Int

        clickCount?.let{
            val newClickCount = it + 1
            marker.tag = newClickCount
            Toast.makeText(
                    this,
                    "${marker.title} has been clicked $newClickCount times. ",
                    Toast.LENGTH_SHORT
            ).show()
        }
    return false
}



    fun getDiscriptorFromDrawable(drawableId : Int) : BitmapDescriptor {
        var bitmapDrawable:BitmapDrawable
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bitmapDrawable = getDrawable(drawableId) as BitmapDrawable
        } else {
            bitmapDrawable = resources.getDrawable(drawableId) as BitmapDrawable
        }
        // 마커 크리 변환
        val scaleBitmap = Bitmap.createScaledBitmap(bitmapDrawable.bitmap, 100,100, false)
        return BitmapDescriptorFactory.fromBitmap(scaleBitmap)

    }
}