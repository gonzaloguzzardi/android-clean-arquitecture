package com.learning.app.utils

import android.content.Context
import android.util.Log
import com.learning.app.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TimeUtils {

    companion object {

        private const val TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val SECONDS_IN_MIN = 60
        private const val MINUTES_IN_HOUR = 60
        private const val HOURS_IN_DAY = 24
        private const val HOURS_TO_YESTERDAY = 48

        fun getTimeSinceCreationString(context: Context, creationTime: String?): String? {
            if (creationTime.isNullOrBlank()) return null

            try {
                val format = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
                format.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
                val parsedDate = format.parse(creationTime) ?: return null

                val now = Date()
                val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - parsedDate.time)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - parsedDate.time)
                val hours = TimeUnit.MILLISECONDS.toHours(now.time - parsedDate.time)
                val days = TimeUnit.MILLISECONDS.toDays(now.time - parsedDate.time)

                return when {
                    seconds < SECONDS_IN_MIN -> "${seconds}s"
                    minutes < MINUTES_IN_HOUR -> "${minutes}m"
                    hours < HOURS_IN_DAY -> "${hours}h"
                    hours < HOURS_TO_YESTERDAY -> context.resources.getString(R.string.label_yesterday)
                    else -> "${days}d"
                }
            } catch (e: ParseException) {
                Log.e("TimeUtils", "Error parsing date:${e.message}")
            }
            return null
        }
    }
}