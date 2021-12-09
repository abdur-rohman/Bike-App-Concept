package com.fizhu.bikeappconcept.utils.helper

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fizhu.bikeappconcept.R

object BindingHelper {

    @JvmStatic
    @BindingAdapter("profileImage")
    fun loadImageProfile(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(Uri.parse(imageUrl ?: ""))
            .placeholder(R.drawable.default_user)
            .error(R.drawable.default_user)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(view)
    }

}