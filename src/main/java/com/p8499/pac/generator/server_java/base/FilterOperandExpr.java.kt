package com.p8499.pac.generator.server_java.base

import com.p8499.pac.formatter.formatJava
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import com.p8499.pac.mapItem
import com.p8499.pac.stringItem
import org.apache.velocity.VelocityContext
import java.io.File

fun filterOperandExpr(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("project", project)
    }
    File(folder, "src/main/java/${project.mapItem("envJtee").stringItem("package").replace('.', '/')}/FilterOperandExpr.java").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/server_java/base/FilterOperandExpr.java.vm"].render(context))
        formatJava()
    }
}