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
        val result = (1..repeat).map {
            JinjaTransformer.applyTemplate(context, template)
        }.first()
        println(result)
    }

    @Test
    fun jsltSingleInputFuncs() {
        val template = readResource("templates/single-input-funcs.jslt")
        val context = mapper.readValue(singleInput, nodeTypeRef)
        val result = (1..repeat).map {
            JsltTransformer.applyTemplate(context, template)
        }.first()
        println(result)
    }

    @Test
    fun freeMakerSingleInputFuncs() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            FreeMakerTransformer.applyTemplate(context, "templates/single-input-funcs.ftl")
        }.first()
        println(result)
    }

    @Test
    fun velocitySingleInputFuncs() {
        val context = mapper.readValue(singleInput, mapTypeRef)
        val result = (1..repeat).map {
            VelocityTransformer.applyTemplate(context, "templates/single-input-funcs.vm")
        }.first()
        println(result)
    }

}