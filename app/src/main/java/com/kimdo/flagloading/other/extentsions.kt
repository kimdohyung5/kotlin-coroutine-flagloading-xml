package com.kimdo.flagloading.other

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kimdo.flagloading.R

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .error(R.drawable.ic_android_black_24dp)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}