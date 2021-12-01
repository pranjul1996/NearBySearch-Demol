package com.softradix.nearbysearch.utils

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.softradix.nearbysearch.R


// show toast on activity
fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
// show toast on activity
fun Fragment.toast(message: String?) {
    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
}
// view gone
fun View.makeGone() {
    this.visibility = View.GONE
}

// view gone
fun View.makeInVisible() {
    this.visibility = View.INVISIBLE
}
fun ImageView.loadImageWithoutShimmer(url: String = "", progressBar: ProgressBar) {
    progressBar.makeVisible()
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_placeholder_home)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_placeholder_home)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.makeGone()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.makeGone()
                return false
            }
        })
        .into(this)
}


// view Visible
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}
