package com.p8499.pac.generator.android_java

import com.jayway.jsonpath.JsonPath
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import org.apache.commons.lang.StringUtils
import org.apache.velocity.VelocityContext
import java.io.File
import java.util.*

fun strings(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("Integer", Int::class.java)
        put("String", String::class.java)
        put("Math", Math::class.java)
        put("Calendar", Calendar::class.java)
        put("JsonPath", JsonPath::class.java)
        put("StringUtils", StringUtils::class.java)
        put("project", project)
    }
    File(folder, "src/main/res/values/strings.xml").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/android_java/strings.xml.vm"].render(context))
    }
}