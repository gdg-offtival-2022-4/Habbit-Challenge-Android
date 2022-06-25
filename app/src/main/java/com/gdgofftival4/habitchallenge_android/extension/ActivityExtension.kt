package com.gdgofftival4.habitchallenge_android.extension

import androidx.activity.ComponentActivity
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.EventObserver

fun <T> ComponentActivity.observeEvent(
    event: EventLiveData<T>,
    onEventUnhandledContent: (T) -> Unit
) {
    event.observe(this, EventObserver(onEventUnhandledContent))
}
