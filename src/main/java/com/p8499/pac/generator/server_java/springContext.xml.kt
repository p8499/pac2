package com.p8499.pac.generator.server_java

import com.p8499.pac.formatter.formatXml
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import org.apache.velocity.VelocityContext
import java.io.File

fun springContext(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("project", project)
    }
    File(folder, "src/main/webapp/WEB-INF/springContext.xml").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/server_java/springContext.xml.vm"].render(context))
        formatXml()
    }
}