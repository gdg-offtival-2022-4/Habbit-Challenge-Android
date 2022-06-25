package com.gdgofftival4.habitchallenge_android.extension

import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.gdgofftival4.habitchallenge_android.R

fun ImageView.setImageUri(uri: Uri, @DrawableRes default: Int = ResourcesCompat.ID_NULL) {
    Glide.with(context).load(uri).centerCrop().placeholder(default).into(this)
}

fun ImageView.setCircleImageUri(uri: Uri, @DrawableRes default: Int = ResourcesCompat.ID_NULL) {
    Glide.with(context).load(uri).circleCrop().placeholder(default).into(this)
}

fun TextView.setTextIfNew(text: CharSequence?) {
    if (TextUtils.equals(this.text, text).not()) {
        setText(text)
    }
}