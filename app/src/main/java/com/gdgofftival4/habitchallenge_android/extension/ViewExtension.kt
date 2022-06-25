package com.gdgofftival4.habitchallenge_android.extension

import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gdgofftival4.habitchallenge_android.R

fun ImageView.setImageUri(uri: Uri) {
    Glide.with(context).load(uri).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(this)
}

fun ImageView.setCircleImageUri(uri: Uri) {
    Glide.with(context).load(uri).circleCrop().placeholder(R.drawable.ic_launcher_foreground).into(this)
}

fun TextView.setTextIfNew(text: CharSequence?) {
    if (TextUtils.equals(this.text, text).not()) {
        setText(text)
    }
}