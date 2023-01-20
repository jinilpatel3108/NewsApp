package com.example.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils {


    @RequiresApi(Build.VERSION_CODES.O)
    companion object {

        final val API_KEY : String = "66ac55a588a94186be5858f7672344fb"

        final val countryMap = mapOf(
            "UAE" to "ae",
            "Argentina" to "ar",
            "Austria" to "at",
            "Australia" to "au",
            "Belgium" to "be",
            "Bulgaria" to "bg",
            "Brazil" to "br",
            "Canada" to "ca",
            "Switzerland" to "ch",
            "China" to "cn",
            "Colombia" to "co",
            "Cuba" to "cu",
            "Czechia" to "cz",
            "India" to "in"
        )

        fun dateFormatChanger(date: String): String{
            val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)

            return parsedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        }
    }

}