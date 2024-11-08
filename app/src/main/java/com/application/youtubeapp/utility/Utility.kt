package com.application.youtubeapp.utility

import android.os.Build
import java.time.Duration

fun String.getTimestampFromISO(): Long {

    // Parse the duration
    val duration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Duration.parse(this)
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    // Get the duration in milliseconds
    return duration.toMillis()
}
