package org.example

import com.hubspot.jinjava.Jinjava
import com.hubspot.jinjava.lib.fn.ELFunctionDefinition


object JinjaTransformer {
    private val jinjava = Jinjava()

    private val timeNowFunc =
        ELFunctionDefinition(
            "",
            "timeNow",
            CustomFunctions::class.java,
            "timeNow"
        )
    private val formatVehicleFunc =
        ELFunctionDefinition(
            "",
            "formatVehicle",
            CustomFunctions::class.java,
            "formatVehicle",
            String::class.java,
            String::class.java,
            String::class.java,
            String::class.java
        )
    private val mapCurrencyFunc =
        ELFunctionDefinition(
            "",
            "mapCurrency",
            CustomFunctions::class.java,
            "mapCurrency",
            String::class.java
        )


    init {
        jinjava.globalContext.registerFunction(timeNowFunc)
        jinjava.globalContext.registerFunction(mapCurrencyFunc)
        jinjava.globalContext.registerFunction(formatVehicleFunc)
    }

    fun applyTemplate(context: Map<String, Any?>, template: String?): String {
        return jinjava.render(template, context)
    }

}