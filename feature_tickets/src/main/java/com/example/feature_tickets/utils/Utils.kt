package com.example.feature_tickets.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun calculateFlightTime(departure: String, arrival: String): Double {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val departureTime = LocalDateTime.parse(departure, formatter)
    val arrivalTime = LocalDateTime.parse(arrival, formatter)
    val duration = Duration.between(departureTime, arrivalTime)
    val hours = duration.toHours().toDouble()
    val minutes = duration.toMinutes() % 60
    val totalTime = hours + (minutes.toDouble() / 60.0)
    val roundedTime = (totalTime * 10).toInt() / 10.0
    return roundedTime
}