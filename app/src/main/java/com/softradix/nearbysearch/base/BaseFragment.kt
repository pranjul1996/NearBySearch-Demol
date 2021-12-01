package com.softradix.nearbysearch.base

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.softradix.nearbysearch.R
import com.softradix.nearbysearch.utils.Utilities

abstract class BaseFragment : Fragment() {
    private var mActivity: FragmentActivity? = null
    private var mProgressDialog: Dialog? = null
    private var mUploadPhotoDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity
    }

    // function for call fragment
    fun callFragment(view: View, fragmentId: Int) {
        view.findNavController().navigate(fragmentId)
    }

    // method overloading
    fun callFragment(view: View, fragmentId: Int, bundle: Bundle) {
        view.findNavController().navigate(fragmentId, bundle)
    }

    // function for call activity
    fun callActivity(context: AppCompatActivity, activity: AppCompatActivity) {
        startActivity(
            Intent(context, activity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        )
        context.finishAffinity()
    }

    fun showLoading(show: Boolean?) {
        if (show == true) showProgress() else hideProgress()
    }

    private fun showProgress() {
        if (mProgressDialog == null) {
            mActivity?.let {
                mProgressDialog = Dialog(it, android.R.style.Theme_Translucent)
                mProgressDialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
                mProgressDialog?.setContentView(R.layout.loader_layout)
                mProgressDialog?.setCancelable(false)
            }
        }

        mProgressDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        mProgressDialog?.window?.statusBarColor = Color.parseColor("#80000000")
        mProgressDialog?.show()
    }


    private fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog?.isShowing == true) {
            mProgressDialog?.dismiss()
        }
    }
    fun getLocationFromLatLong(lat: Double, long: Double): String? {
        var location: String? = ""
        if (Utilities.isNetworkAvailable(activity)) {
            val geoCoder = Geocoder(activity)
            val addressList =
                geoCoder.getFromLocation(lat, long, 1)
            location = if (addressList.size > 0) {
                "${addressList[0].getAddressLine(0) ?: ""} ${addressList[0].locality},${addressList[0].countryName}"
            } else {
                "N/A"
            }
        }
        return location
    }
}