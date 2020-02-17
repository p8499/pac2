package com.p8499.pac.generator

import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import java.io.StringWriter

val velocityEngine = VelocityEngine().apply {
    setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath")
    setProperty("classpath." + RuntimeConstants.RESOURCE_LOADER + ".class", ClasspathResourceLoader::class.java.name)
    setProperty("directive.set.null.allowed", true)
    init()
}

operator fun VelocityEngine.get(name: String): Template = getTemplate(name)

fun Template.render(context: VelocityContext): String {
    val bufferWriter = StringWriter()
    merge(context, bufferWriter)
    bufferWriter.flush()
    bufferWriter.close()
    return bufferWriter.toString().trim()
}

