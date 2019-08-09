package com.p8499.pac.generator.database.module

import com.jayway.jsonpath.JsonPath
import com.p8499.pac.formatter.formatSql
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import com.p8499.pac.listItem
import com.p8499.pac.mapItem
import com.p8499.pac.readList
import com.p8499.pac.stringItem
import org.apache.commons.lang.StringUtils
import org.apache.velocity.VelocityContext
import java.io.File
import java.util.*

fun dropTable(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("Integer", Int::class.java)
        put("String", String::class.java)
        put("Math", Math::class.java)
        put("Calendar", Calendar::class.java)
        put("JsonPath", JsonPath::class.java)
        put("StringUtils", StringUtils::class.java)
        put("project", project)
    }
    project.listItem("modules").map { it as Map<*, *> }.forEachIndexed { index, module ->
        context.put("index", index)
        File(folder, "${module.stringItem("dataSource")}_drop_table_${module.stringItem("id")}.sql").apply {
            if (!parentFile.exists()) parentFile.mkdirs()
            writeText(velocityEngine["vm/database/module/%s_drop_table_%s.sql.vm"].render(context))
            formatSql(project.readList("$.envJtee.dataSources[?(@.id=='${module.stringItem("dataSource")}')]").mapItem(0).stringItem("databaseType"))
        }
    }
}