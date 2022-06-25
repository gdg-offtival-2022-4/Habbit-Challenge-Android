package com.gdgofftival4.habitchallenge_android.extension

import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

fun ImageView.setCircleImageUri(uri: Uri) {
    Glide.with(context).load(uri).circleCrop().into(this)
}

fun TextView.setTextIfNew(text: CharSequence?) {
    if (TextUtils.equals(this.text, text).not()) {
        setText(text)
    }
}