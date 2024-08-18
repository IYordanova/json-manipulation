import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import org.example.Utils.mapper
import org.example.Utils.readResource
import org.junit.jupiter.api.Assertions.assertEquals

abstract class TransformerTest {
    protected val singleInput = readResource("examples/single-input.json")
    protected val expectedSingleInput = readResource("expected/single-input.json")

    protected val mapTypeRef = object : TypeReference<Map<String, Any?>>() {}
    protected val arrNodeTypeRef = object : TypeReference<ArrayNode>() {}
    protected val nodeTypeRef = object : TypeReference<JsonNode>() {}

    protected val repeat = 10;

    protected fun assertJsonEquals(expected: String, actual: String) {
        assertEquals(
            mapper.readValue(expected, mapTypeRef),
            mapper.readValue(actual, mapTypeRef)
        )
    }
}