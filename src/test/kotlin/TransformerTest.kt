import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import org.example.Utils.readResource
import java.util.function.Supplier

abstract class TransformerTest {
    protected val singleInput = readResource("examples/single-input.json")

    protected val mapTypeRef = object : TypeReference<Map<String, Any?>>() {}
    protected val arrNodeTypeRef = object : TypeReference<ArrayNode>() {}
    protected val nodeTypeRef = object : TypeReference<JsonNode>() {}

    private val repeat = 100

    protected fun calcAvgTime(func: Supplier<Any>) {
        val times = mutableListOf<Long>()
        (1..repeat).forEach { _ ->
            val start = System.nanoTime()
            func.get()
            times.add(System.nanoTime() - start)
        }
        println(times)
        println("Avg time: ${times.average() / 1000000}, ${times.min() / 1000000}, ${times.max() / 1000000}")
    }
}