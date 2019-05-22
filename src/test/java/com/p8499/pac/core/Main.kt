package com.p8499.pac.core

import com.fasterxml.jackson.databind.ObjectMapper


fun main(args: Array<String>) {
//    val fd = deserialize(Thread.currentThread().contextClassLoader.getResource("fd.json").readText())
    val sales = deserialize(Thread.currentThread().contextClassLoader.getResource("sales.json").readText())
//    println(fd)
//    println(serialize(fd))
    println(sales)
    println(serialize(sales))
//    val context = JXPathContext.newContext(sales)
//    println(context.getValue(""))
//    println(context.getValue("/"))
//    println(context.getValue("."))
//    println(context.getValue("envJtee"))
//    println(context.getValue("/envJtee"))
//    println(context.getValue("./envJtee"))
//    println(context.getValue("envJtee/dataSources[1]"))
//    println(context.getValue("/envJtee/dataSources[1]"))
//    println(context.getValue("./envJtee/dataSources[1]"))
//    println(context.getValue("envJtee/dataSources[1]/userName"))
//    println(context.getValue("/envJtee/dataSources[1]/userName"))
//    println(context.getValue("./envJtee/dataSources[1]/userName"))
//    val context2 = JXPathContext.newContext(ProjectTreeItem(sales))
//    println(context2.getValue("/"))
}

fun deserialize(str: String): Project = ObjectMapper().readValue(str, Project::class.java)
fun serialize(prj: Project): String = ObjectMapper().writeValueAsString(prj)
//        val new = Module(id = "abc")
//        (tree.root.children[2] as ModulesTreeItem).core += new
//        (tree.root.children[2] as ModulesTreeItem).children += ModuleTreeItem(new)