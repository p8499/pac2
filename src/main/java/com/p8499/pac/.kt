package com.p8499.pac

import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import com.p8499.pac.core.Project
import java.io.File
import java.net.URL

private val objectMapper = ObjectMapper()
fun resource(name: String): URL = Thread.currentThread().contextClassLoader.getResource(name)
fun readResource(name: String): Project = objectMapper.readValue(resource(name).readText(), Project::class.java)
fun readFile(file: File): Project = objectMapper.readValue(file, Project::class.java)
fun readFile(path: String): Project = readFile(File(path))
fun readFileAsMap(file: File): Map<*, *> = objectMapper.readValue(file, Map::class.java)
fun readFileAsMap(path: String): Map<*, *> = readFileAsMap(File(path))
fun writeFile(project: Project, file: File) = objectMapper.writeValue(file, project)
fun writeFile(project: Project, path: String) = writeFile(project, File(path))
fun <T> MutableList<T>.move(a: Int, b: Int): Int {
    add(b, this[a])
    removeAt(if (b < a) a + 1 else a)
    return if (b < a) b else b - 1
}

fun Map<*, *>.stringItem(name: String): String = this[name] as String
fun Map<*, *>.listItem(name: String): List<*> = this[name] as List<*>
fun Map<*, *>.mapItem(name: String): Map<*, *> = this[name] as Map<*, *>
fun List<*>.stringItem(index: Int): String = this[index] as String
fun List<*>.listItem(index: Int): List<*> = this[index] as List<*>
fun List<*>.mapItem(index: Int): Map<*, *> = this[index] as Map<*, *>
fun Map<*, *>.readMap(path: String): Map<*, *> = JsonPath.parse(this).read<Map<*, *>>(path)
fun Map<*, *>.readList(path: String): List<*> = JsonPath.parse(this).read<List<*>>(path)
