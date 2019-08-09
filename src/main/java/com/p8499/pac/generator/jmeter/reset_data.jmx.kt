package com.p8499.pac.generator.jmeter

import com.jayway.jsonpath.JsonPath
import com.p8499.pac.formatter.formatXml
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import org.apache.commons.lang.ArrayUtils
import org.apache.commons.lang.StringUtils
import org.apache.velocity.VelocityContext
import java.io.File
import java.util.*

fun resetData(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("Class", Class::class.java)
        put("Integer", Integer::class.java)
        put("String", String::class.java)
        put("Math", Math::class.java)
        put("Calendar", Calendar::class.java)
        put("JsonPath", JsonPath::class.java)
        put("StringUtils", StringUtils::class.java)
        put("ArrayUtils", ArrayUtils::class.java)
        put("project", project)
    }
    File(folder, "reset_data.jmx").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/jmeter/reset_data.jmx.vm"].render(context))
        formatXml()
    }
}
