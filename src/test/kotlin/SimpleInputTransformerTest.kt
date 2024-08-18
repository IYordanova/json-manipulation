import org.example.FreeMakerTransformer
import org.example.JinjaTransformer
import org.example.JsltTransformer
import org.example.Utils.mapper
import org.example.Utils.readResource
import org.example.VelocityTransformer
import org.junit.jupiter.api.Test

class SimpleInputTransformerTest : TransformerTest() {

    @Test
    fun jinjaSingleInput() {
        val template = readResource("templates/single-input.jinja")
        val context = mapper.readValue(singleInput, mapTypeRef)
         (1..repeat).forEach { _ ->
             JinjaTransformer.applyTemplate(context, template)
        }
    }

    @Test
    fun jsltSingleInput() {
        val template = readResource("templates/single-input.jslt")
        val context = mapper.readValue(singleInput, nodeTypeRef)
        (1..repeat).forEach { _ ->
            JsltTransformer.applyTemplate(context, template)
        }
    }

    @Test
    fun freeMakerSingleInput() {
        val context = mapper.readValue(singleInput, mapTypeRef)
         (1..repeat).forEach { _ ->
            FreeMakerTransformer.applyTemplate(context, "templates/single-input.ftl")
        }
    }

    @Test
    fun velocitySingleInput() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        (1..repeat).forEach { _ ->
            VelocityTransformer.applyTemplate(context, "templates/single-input.vm")
        }
    }

}