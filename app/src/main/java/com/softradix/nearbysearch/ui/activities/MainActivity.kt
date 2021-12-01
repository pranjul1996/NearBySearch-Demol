package com.softradix.nearbysearch.ui.activities

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.softradix.nearbysearch.databinding.ActivityMainBinding
import com.softradix.nearbysearch.network.NetworkChangeReceiver
import com.softradix.nearbysearch.utils.Constants
import com.softradix.nearbysearch.utils.LocationService
import com.softradix.nearbysearch.utils.Preferences
import com.softradix.nearbysearch.utils.saveValue

const val PERMISSION_ID = 42

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mInterNetCheckReceiver: BroadcastReceiver
    private var locationService: LocationService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.root.apply {
            setContentView(this)
        }


        mInterNetCheckReceiver =
            NetworkChangeReceiver()      // register check internet broadcast receiver
        @Suppress("DEPRECATION")
        registerReceiver(
            mInterNetCheckReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        if (isLocationEnabled() && checkPermissions()) {
            getLocationUpdate()
        } else {
            requestPermissions()
        }

    }

    private fun getLocationUpdate() {
        locationService = LocationService(this)
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocationUpdate()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mInterNetCheckReceiver)
    }

}