package com.example.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils {


    @RequiresApi(Build.VERSION_CODES.O)
    companion object {

        final val API_KEY : String = "66ac55a588a94186be5858f7672344fb"

        fun dateFormatChanger(date: String): String{
            val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)

            return parsedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        }
    }

}