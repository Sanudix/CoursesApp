package com.example.domain.utils

class FormatDateFromJson {
    fun formatDate(dateString: String): String {
        return try {
            val parts = dateString.split("-")
            if (parts.size != 3) return dateString

            val year = parts[0]
            val month = parts[1].toInt()
            val day = parts[2].toInt()

            val monthName = when (month) {
                1 -> "Января"
                2 -> "Февраля"
                3 -> "Марта"
                4 -> "Апреля"
                5 -> "Мая"
                6 -> "Июня"
                7 -> "Июля"
                8 -> "Августа"
                9 -> "Сентября"
                10 -> "Октября"
                11 -> "Ноября"
                12 -> "Декабря"
                else -> ""
            }

            "$day $monthName $year"
        } catch (e: Exception) {
            dateString
        }
    }
}