package com.p8499.pac.generator.node_js.base

import com.p8499.pac.formatter.formatJs
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import org.apache.velocity.VelocityContext
import java.io.File

fun utils(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("project", project)
    }
    File(folder, "src/components/gen/utils.ts").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/node_js/base/utils.ts.vm"].render(context))
        formatJs()
    }
}