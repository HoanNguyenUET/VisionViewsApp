package com.example.newsapp.ultils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object TimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatIsoTime(isoString: String): String {
        val zonedDateTime = ZonedDateTime.parse(isoString)
        val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")
        return zonedDateTime.format(formatter)
    }
}