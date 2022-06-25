package com.gdgofftival4.habitchallenge_android.extension

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.EventObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> ComponentActivity.observeEvent(
    event: EventLiveData<T>,
    onEventUnhandledContent: (T) -> Unit
) {
    event.observe(this, EventObserver(onEventUnhandledContent))
}

fun ComponentActivity.repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch { lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block) }
}