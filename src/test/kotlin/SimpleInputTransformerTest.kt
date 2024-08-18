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

}