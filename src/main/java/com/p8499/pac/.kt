package com.p8499.pac

import com.fasterxml.jackson.databind.ObjectMapper
import com.p8499.pac.controller.scene
import com.p8499.pac.core.Project
import javafx.application.Application
import javafx.stage.Stage
import java.io.File
import java.net.URL

class PACApplication : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.scene = scene("vm/application.fxml")
        primaryStage.isMaximized = true
        primaryStage.show()
    }
}

fun main(args: Array<String>) = Application.launch(PACApplication::class.java, *args)

private val objectMapper = ObjectMapper()
fun resource(name: String): URL = Thread.currentThread().contextClassLoader.getResource(name)
fun readResource(name: String): Project = objectMapper.readValue(resource(name).readText(), Project::class.java)
fun readFile(file: File): Project = objectMapper.readValue(file, Project::class.java)
fun readFile(path: String): Project = readFile(File(path))
fun writeFile(project: Project, file: File) = objectMapper.writeValue(file, project)
fun writeFile(project: Project, path: String) = writeFile(project, File(path))
fun <T> MutableList<T>.move(a: Int, b: Int): Int {
    add(b, this[a])
    removeAt(if (b < a) a + 1 else a)
    return if (b < a) b else b - 1
}