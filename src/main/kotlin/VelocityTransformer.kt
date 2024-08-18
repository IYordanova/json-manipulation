package org.example

import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import java.io.StringWriter


object VelocityTransformer {

    private val velocityEngine = VelocityEngine()
    private val defaultContext = VelocityContext().apply {
        put("customFunctions", CustomFunctions::class.java)
    }

    init {
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath")
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader::class.java.name)
        velocityEngine.init()
    }

    fun loadTemplate(template: String): Template = velocityEngine.getTemplate(template)

    fun applyTemplate(context: Map<String, Any?>, template: Template): String {
        val vContext = VelocityContext(context, defaultContext)

        val writer = StringWriter()
        template.merge(vContext, writer)
        return writer.toString()
    }

}