package com.p8499.pac.generator.server_java

import com.jayway.jsonpath.JsonPath
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import org.apache.velocity.VelocityContext
import java.io.File

fun build(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("JsonPath", JsonPath::class.java)
        put("project", project)
    }
    File(folder, "build.gradle").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/server_java/build.gradle.vm"].render(context))
    }
}