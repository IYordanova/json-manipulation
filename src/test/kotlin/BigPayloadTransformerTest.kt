import com.fasterxml.jackson.databind.node.JsonNodeFactory
import org.example.FreeMakerTransformer
import org.example.JinjaTransformer
import org.example.JsltTransformer
import org.example.Utils.mapper
import org.example.Utils.readResource
import org.example.VelocityTransformer
import org.junit.jupiter.api.Test

class BigPayloadTransformerTest : TransformerTest() {

    @Test
    fun jinjaMultiInput() {
        val template = readResource("templates/multi-input.jinja")
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = mutableMapOf<String, Any?>()

        // 100
        context["results"] = (0..100).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JinjaTransformer.applyTemplate(context, template) }
        // 1000
        context["results"] = (0..900).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JinjaTransformer.applyTemplate(context, template) }
        // 10_000
        context["results"] = (0..9000).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JinjaTransformer.applyTemplate(context, template) }
        // 100_000
        context["results"] = (0..60_000).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JinjaTransformer.applyTemplate(context, template) }
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
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JsltTransformer.applyTemplate(context, template) }
        // 1000
        context.put("results", nineHundred)
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JsltTransformer.applyTemplate(context, template) }
        // 10_000
        context.put("results", nineThousand)
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JsltTransformer.applyTemplate(context, template) }
        // 100_000
        context.put("results", sixtyThousand)
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { JsltTransformer.applyTemplate(context, template) }
    }

    @Test
    fun freeMakerMultiInput() {
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = mutableMapOf<String, Any?>()
        context["results"] = (0..1).map { singleResult }
        val template = FreeMakerTransformer.loadTemplate("templates/multi-input.ftl")

        // 100
        context["results"] = (0..100).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { FreeMakerTransformer.applyTemplate(context, template) }
        // 1000
        context["results"] = (0..900).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { FreeMakerTransformer.applyTemplate(context, template) }
        // 10_000
        context["results"] = (0..9000).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { FreeMakerTransformer.applyTemplate(context, template) }
        // 100_000
        context["results"] = (0..60_000).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { FreeMakerTransformer.applyTemplate(context, template) }
    }

    @Test
    fun velocityMultiInput() {
        val singleResult = mapper.readValue(singleInput, mapTypeRef)
        val context = mutableMapOf<String, Any?>()
        context["results"] = (0..1).map { singleResult }
        val template = VelocityTransformer.loadTemplate("templates/multi-input.vm")

        // 100
        context["results"] = (0..100).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { VelocityTransformer.applyTemplate(context, template) }
        // 1000
        context["results"] = (0..900).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { VelocityTransformer.applyTemplate(context, template) }
        // 10_000
        context["results"] = (0..9000).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { VelocityTransformer.applyTemplate(context, template) }
        // 100_000
        context["results"] = (0..60_000).map { singleResult }
        println("Size " + mapper.writeValueAsBytes(context).size)
        calcAvgTime { VelocityTransformer.applyTemplate(context, template) }
    }

}