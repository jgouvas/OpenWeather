package com.openweathermap.test.utils

import java.util.*

class DateCalculationUtils {

    companion object {
        fun diffBetweenDates(date1: Long, date2: Long): Int {
            val dateDiff = (date2 - date1) / 60 / 60 / 24
            return dateDiff.toInt()
        }

        fun getStartOfDayInMillis(): Long {
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar.timeInMillis
        }
    }
}