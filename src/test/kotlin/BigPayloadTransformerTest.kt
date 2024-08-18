import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import org.example.FreeMakerTransformer
import org.example.JinjaTransformer
import org.example.JsltTransformer
import org.example.Utils.mapper
import org.example.Utils.readResource
import org.example.VelocityTransformer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class TransformerTest {
    private val singleInput = readResource("examples/single-input.json")
    private val expectedSingleInput = readResource("expected/single-input.json")

    private val mapTypeRef = object : TypeReference<Map<String, Any?>>() {}
    private val arrNodeTypeRef = object : TypeReference<ArrayNode>() {}
    private val nodeTypeRef = object : TypeReference<JsonNode>() {}

    private fun assertJsonEquals(expected: String, actual: String) {
        assertEquals(
            mapper.readValue(expected, mapTypeRef),
            mapper.readValue(actual, mapTypeRef)
        )
    }

    // ------------- Simple Example -------------

    private val repeat = 1_000;

    @Test
    fun jinjaSingleInput() {
        val template = readResource("templates/single-input.jinja")
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            JinjaTransformer.applyTemplate(context, template)
        }.first()
        assertJsonEquals(expectedSingleInput, result)
    }

    @Test
    fun jsltSingleInput() {
        val template = readResource("templates/single-input.jslt")
        val context = mapper.readValue(singleInput, nodeTypeRef)
        val result = (1..repeat).map {
            JsltTransformer.applyTemplate(context, template)
        }.first()
        assertJsonEquals(expectedSingleInput, mapper.writeValueAsString(result))
    }

    @Test
    fun freeMakerSingleInput() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            FreeMakerTransformer.applyTemplate(context, "templates/single-input.ftl")
        }.first()
        assertJsonEquals(expectedSingleInput, result)
    }

    @Test
    fun velocitySingleInput() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            VelocityTransformer.applyTemplate(context, "templates/single-input.vm")
        }.first()
        assertJsonEquals(expectedSingleInput, result)
    }

    // ------------- Custom Functions -------------
    @Disabled
    @Test
    fun jinjaSingleInputFuncs() {
        val template = readResource("templates/single-input-funcs.jinja")
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            JinjaTransformer.applyTemplate(context, template)
        }.first()
        println(result)
    }
    @Disabled
    @Test
    fun jsltSingleInputFuncs() {
        val template = readResource("templates/single-input-funcs.jslt")
        val context = mapper.readValue(singleInput, nodeTypeRef)
        val result = (1..repeat).map {
            JsltTransformer.applyTemplate(context, template)
        }.first()
        println(result)
    }
    @Disabled
    @Test
    fun freeMakerSingleInputFuncs() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            FreeMakerTransformer.applyTemplate(context, "templates/single-input-funcs.ftl")
        }.first()
        println(result)
    }

    // ------------- Big Payload -------------
    @Disabled
    @Test
    fun jinjaMultiInput() {
        val template = readResource("templates/multi-input.jinja")
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = mutableMapOf<String, Any?>()

        // 100
        context["results"] = (0..100).map { singleResult }
        (1..repeat).forEach { _ -> JinjaTransformer.applyTemplate(context, template) }
        // 1000
        context["results"] = (0..900).map { singleResult }
        (1..repeat).forEach { _ -> JinjaTransformer.applyTemplate(context, template) }
        // 10_000
        context["results"] = (0..9000).map { singleResult }
        (1..repeat).forEach { _ -> JinjaTransformer.applyTemplate(context, template) }
        // 100_000
        context["results"] = (0..60_000).map { singleResult }
        (1..repeat).forEach { _ -> JinjaTransformer.applyTemplate(context, template) }
    }
    @Disabled
    @Test
    fun jsltMultiInput() {
        val template = readResource("templates/multi-input.jslt")
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = JsonNodeFactory.instance.objectNode()

        val hundredResults = mapper.convertValue((0..100).map { singleResult }, arrNodeTypeRef)
        val nineHundred = mapper.convertValue((0..900).map { singleResult }, arrNodeTypeRef)
        val nineThousand = mapper.convertValue((0..9000).map { singleResult }, arrNodeTypeRef)
        val sixtyThousand = mapper.convertValue((0..60_000).map { singleResult }, arrNodeTypeRef)

        // 100
        context.put("results", hundredResults)
        (1..repeat).forEach { _ -> JsltTransformer.applyTemplate(context, template) }
        // 1000
        context.put("results", nineHundred)
        (1..repeat).forEach { _ -> JsltTransformer.applyTemplate(context, template) }
        // 10_000
        context.put("results", nineThousand)
        (1..repeat).forEach { _ -> JsltTransformer.applyTemplate(context, template) }
        // 100_000
        context.put("results", sixtyThousand)
        (1..repeat).forEach { _ -> JsltTransformer.applyTemplate(context, template) }
    }
    @Disabled
    @Test
    fun freeMakerMultiInput() {
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = mutableMapOf<String, Any?>()
        context["results"] = (0..1).map { singleResult }
        val template = "templates/multi-input.ftl"

        // 100
        context["results"] = (0..100).map { singleResult }
        (1..repeat).forEach { _ -> FreeMakerTransformer.applyTemplate(context, template) }
        // 1000
        context["results"] = (0..900).map { singleResult }
        (1..repeat).forEach { _ -> FreeMakerTransformer.applyTemplate(context, template) }
        // 10_000
        context["results"] = (0..9000).map { singleResult }
        (1..repeat).forEach { _ -> FreeMakerTransformer.applyTemplate(context, template) }
        // 100_000
        context["results"] = (0..60_000).map { singleResult }
        (1..repeat).forEach { _ -> FreeMakerTransformer.applyTemplate(context, template) }
    }

}