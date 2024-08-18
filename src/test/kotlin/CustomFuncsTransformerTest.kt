import org.example.FreeMakerTransformer
import org.example.JinjaTransformer
import org.example.JsltTransformer
import org.example.Utils.mapper
import org.example.Utils.readResource
import org.example.VelocityTransformer
import org.junit.jupiter.api.Test

class CustomFuncsTransformerTest : TransformerTest() {

    @Test
    fun jinjaSingleInputFuncs() {
        val template = readResource("templates/single-input-funcs.jinja")
        val context = mapper.readValue(singleInput, mapTypeRef)
        calcAvgTime { JinjaTransformer.applyTemplate(context, template) }
    }

    @Test
    fun jsltSingleInputFuncs() {
        val template = readResource("templates/single-input-funcs.jslt")
        val context = mapper.readValue(singleInput, nodeTypeRef)
        calcAvgTime { JsltTransformer.applyTemplate(context, template) }
    }

    @Test
    fun freeMakerSingleInputFuncs() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val template = FreeMakerTransformer.loadTemplate("templates/single-input-funcs.ftl")
        calcAvgTime { FreeMakerTransformer.applyTemplate(context, template) }
    }

    @Test
    fun velocitySingleInputFuncs() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val template = VelocityTransformer.loadTemplate("templates/single-input-funcs.vm")
        calcAvgTime { VelocityTransformer.applyTemplate(context, template) }
    }

}