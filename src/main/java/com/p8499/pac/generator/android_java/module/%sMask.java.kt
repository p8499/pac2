package com.p8499.pac.generator.android_java.module

import com.jayway.jsonpath.JsonPath
import com.p8499.pac.formatter.formatJava
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import com.p8499.pac.listItem
import com.p8499.pac.mapItem
import com.p8499.pac.stringItem
import org.apache.commons.lang.StringUtils
import org.apache.velocity.VelocityContext
import java.io.File
import java.util.*

fun mask(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
       put("Integer", Int::class.java)
       put("String", String::class.java)
       put("Math", Math::class.java)
       put("Calendar", Calendar::class.java)
       put("JsonPath", JsonPath::class.java)
       put("StringUtils", StringUtils::class.java)
       put("project", project)
    }
    project.listItem("modules").map { it as Map<*, *> }.forEachIndexed { index, module ->
        context.put("index", index)
        File(folder, "src/main/java/${project.mapItem("envAndroid").stringItem("package").replace('.', '/')}/mask/${module.stringItem("alias")}Mask.java").apply {
            if (!parentFile.exists()) parentFile.mkdirs()
            writeText(velocityEngine["vm/android_java/module/%sMask.java.vm"].render(context))
            formatJava()
        }
    }
}