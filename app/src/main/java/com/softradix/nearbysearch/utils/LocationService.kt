package com.softradix.nearbysearch.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 1000L * 10

open class LocationService (var context: Context){

    /**
     * The desired interval for location updates.
    Inexact. Updates may be more or less frequent.
     */
    private val updateIntervelInMilliseconds: Long = 10000

    /**
     * The fastest rate for active location updates. Exact.
    Updates will never be more frequent
     * than this value.
     */
    private val fastestUpdateIntervelInMilliseconds = updateIntervelInMilliseconds / 2

    /**
     * Stores the types of location services the
    client is interested in using. Used for checking
     * settings to determine
    if the device has optimal location settings.
     */
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null

    //Location Callback
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val currentLocation: Location = locationResult.lastLocation

//            onLocationChange(currentLocation)
            Preferences.prefs?.saveValue(
                Constants.CURRENT_LOCATION,
                "${currentLocation.latitude},${currentLocation.longitude}"
            )
            Log.d(
                "Locations",
                currentLocation.latitude.toString() + "," + currentLocation.longitude
            )
            //Share/Publish Location
        }
    }

    init {
        locationRequest = LocationRequest.create()
        locationRequest?.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)
        startLocationUpdates()
    }


    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocationClient?.requestLocationUpdates(
            locationRequest!!,
            locationCallback, Looper.myLooper()!!
        )
    }
}
