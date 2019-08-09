package com.p8499.pac.generator.android_java

import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import org.apache.velocity.VelocityContext
import java.io.File

fun build(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("project", project)
    }
    File(folder, "build.gradle").apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        writeText(velocityEngine["vm/android_java/build.gradle.vm"].render(context))
    }
}