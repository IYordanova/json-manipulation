package org.example

import freemarker.cache.TemplateNameFormat
import freemarker.core.JSONOutputFormat
import freemarker.template.*
import java.io.StringWriter


object FreeMakerTransformer {
    private val cfg = Configuration(Configuration.VERSION_2_3_31)

    init {
        cfg.setClassForTemplateLoading(FreeMakerTransformer::class.java, "/")
        cfg.defaultEncoding = "UTF-8"
        cfg.templateExceptionHandler = TemplateExceptionHandler.IGNORE_HANDLER
        cfg.logTemplateExceptions = false
        cfg.wrapUncheckedExceptions = true
        cfg.outputFormat = JSONOutputFormat.INSTANCE
        cfg.templateNameFormat = TemplateNameFormat.DEFAULT_2_4_0
        cfg.unsetLocale()
        cfg.setSharedVariable("timeNow", TimeNow2())
        cfg.setSharedVariable("formatVehicle", FormatVehicle2())
        cfg.setSharedVariable("mapCurrency", MapCurrency2())
    }

    fun loadTemplate(template: String): Template {
        val t = cfg.getTemplate(template)
        t.numberFormat = "c"
        return t
    }

    fun applyTemplate(context: Any?, template: Template): String {
        val writer = StringWriter()
        template.process(context, writer)
        return writer.toString()
    }
}

class TimeNow2 : TemplateMethodModel {
    override fun exec(p0: List<Any?>?) =
        CustomFunctions.timeNow()
}

class FormatVehicle2 : TemplateMethodModel {
    override fun exec(p0: List<Any?>?) =
        p0?.get(0)?.toString()?.let { one ->
            p0.get(1)?.toString()?.let { two ->
                p0.get(2)?.toString()?.let { three ->
                    p0.get(3)?.toString()?.let { four ->
                        CustomFunctions.formatVehicle(one, two, three, four)
                    }
                }
            }
        }
}

class MapCurrency2 : TemplateMethodModel {
    override fun exec(p0: List<Any?>?) =
        p0?.get(0)?.toString()?.let { CustomFunctions.mapCurrency(it) }
}

