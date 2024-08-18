import com.fasterxml.jackson.databind.node.JsonNodeFactory
import org.example.FreeMakerTransformer
import org.example.JinjaTransformer
import org.example.JsltTransformer
import org.example.Utils.mapper
import org.example.Utils.readResource
import org.example.VelocityTransformer
import org.junit.jupiter.api.Test

class BigPayloadTransformerTest: TransformerTest() {

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

    @Test
    fun velocityMultiInput() {
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = mutableMapOf<String, Any?>()
        context["results"] = (0..1).map { singleResult }
        val template = "templates/multi-input.vm"

        // 100
        context["results"] = (0..100).map { singleResult }
        (1..repeat).forEach { _ -> VelocityTransformer.applyTemplate(context, template) }
        // 1000
        context["results"] = (0..900).map { singleResult }
        (1..repeat).forEach { _ -> VelocityTransformer.applyTemplate(context, template) }
        // 10_000
        context["results"] = (0..9000).map { singleResult }
        (1..repeat).forEach { _ -> VelocityTransformer.applyTemplate(context, template) }
        // 100_000
        context["results"] = (0..60_000).map { singleResult }
        (1..repeat).forEach { _ -> VelocityTransformer.applyTemplate(context, template) }
    }

}