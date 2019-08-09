package com.p8499.pac.generator.database

import com.p8499.pac.generator.database.data_source.*
import com.p8499.pac.generator.database.module.*
import java.io.File

fun database(project: Map<*, *>, folder: File) {
    createAll(project, folder)
    createTables(project, folder)
    createViews(project, folder)
    dropAll(project, folder)
    dropTables(project, folder)
    dropViews(project, folder)
    createTable(project, folder)
    createView(project, folder)
    createViewBase(project, folder)
    dropTable(project, folder)
    dropView(project, folder)
}