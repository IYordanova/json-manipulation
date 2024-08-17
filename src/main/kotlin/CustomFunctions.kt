package org.example

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

object CustomFunctions {

    @JvmStatic
    fun mapCurrency(currency: String) =
         when (currency) {
            "US dollar" -> "USD"
            "Euro" -> "EUR"
            "British Pound" -> "GBP"
            "Danish Krone" -> "DKK"
            "Seychelles Rupee" -> "SCR"
             else -> "unknown"
        }

    @JvmStatic
    fun formatVehicle(manufacturer: String, model: String, name: String, type: String) =
        "$manufacturer - $model/$name [$type]"

    private val formatter = DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        .appendOffsetId()
        .toFormatter()

    @JvmStatic
    fun timeNow(): String? {
        return runCatching {
            ZonedDateTime.now().format(formatter)
        }.getOrNull()
    }
}