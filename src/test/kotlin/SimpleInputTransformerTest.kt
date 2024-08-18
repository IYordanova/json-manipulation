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
        calcAvgTime { JinjaTransformer.applyTemplate(context, template) }
    }

    @Test
    fun jsltSingleInput() {
        val template = readResource("templates/single-input.jslt")
        val context = mapper.readValue(singleInput, nodeTypeRef)
        calcAvgTime { JsltTransformer.applyTemplate(context, template) }
    }

    @Test
    fun freeMakerSingleInput() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val template = FreeMakerTransformer.loadTemplate("templates/single-input.ftl")
        calcAvgTime { FreeMakerTransformer.applyTemplate(context, template) }
    }

    @Test
    fun velocitySingleInput() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val template = VelocityTransformer.loadTemplate("templates/single-input.vm")
        calcAvgTime { VelocityTransformer.applyTemplate(context, template) }
    }

}