package com.secondslot.seloustev.extentions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener

fun ImageView.loadImage(
    imageUri: String,
    requestListener: RequestListener<Drawable>
) {

    Glide.with(context)
        .load(imageUri)
        .listener(requestListener)
        .centerCrop()
        .into(this)
}