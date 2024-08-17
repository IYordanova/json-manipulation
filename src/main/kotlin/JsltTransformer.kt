package org.example

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.schibsted.spt.data.jslt.Parser
import com.schibsted.spt.data.jslt.Function
import org.example.Utils.mapper


object JsltTransformer {
    private val functions = listOf(
        TimeNow(),
        FormatVehicle(),
        MapCurrency()
    )

    fun applyTemplate(input: JsonNode?, template: String?): JsonNode {
        val jslt = Parser.compileString(template, functions)
        return jslt.apply(input)
    }
}

class TimeNow : Function {
    override fun getName() = "timeNow"

    override fun getMaxArguments() = 0

    override fun getMinArguments() = 0

    override fun call(jsonNode: JsonNode?, jsonNodes: Array<JsonNode?>): JsonNode {
        return JsonNodeFactory.instance.textNode(CustomFunctions.timeNow())
    }
}

class FormatVehicle : Function {
    override fun getName() = "formatVehicle"

    override fun getMaxArguments() = 4

    override fun getMinArguments() = 4

    override fun call(jsonNode: JsonNode?, jsonNodes: Array<JsonNode?>): JsonNode {
        val man = mapper.convertValue(jsonNodes[0], String::class.java)
        val mod = mapper.convertValue(jsonNodes[0], String::class.java)
        val name = mapper.convertValue(jsonNodes[0], String::class.java)
        val type = mapper.convertValue(jsonNodes[0], String::class.java)
        return JsonNodeFactory.instance.textNode(CustomFunctions.formatVehicle(man, mod, name, type))
    }
}

class MapCurrency : Function {
    override fun getName() = "mapCurrency"

    override fun getMaxArguments() = 1

    override fun getMinArguments() = 1

    override fun call(jsonNode: JsonNode?, jsonNodes: Array<JsonNode>): JsonNode {
        return JsonNodeFactory.instance.textNode(
            CustomFunctions.mapCurrency(jsonNodes[0].asText())
        )
    }
}