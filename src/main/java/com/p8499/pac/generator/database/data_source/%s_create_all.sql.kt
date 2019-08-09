package com.p8499.pac.generator.database.data_source

import com.jayway.jsonpath.JsonPath
import com.p8499.pac.formatter.formatSql
import com.p8499.pac.generator.get
import com.p8499.pac.generator.render
import com.p8499.pac.generator.velocityEngine
import com.p8499.pac.listItem
import com.p8499.pac.mapItem
import com.p8499.pac.stringItem
import org.apache.commons.lang.StringUtils
import org.apache.velocity.VelocityContext
import java.io.File
import java.util.*

fun createAll(project: Map<*, *>, folder: File) {
    val context = VelocityContext().apply {
        put("Integer", Integer::class.java)
        put("String", String::class.java)
        put("Math", Math::class.java)
        put("Calendar", Calendar::class.java)
        put("JsonPath", JsonPath::class.java)
        put("StringUtils", StringUtils::class.java)
        put("project", project);
    }
    project.mapItem("envJtee").listItem("dataSources").map { it as Map<*, *> }.forEachIndexed { index, dataSource ->
        context.put("index", index)
        File(folder, "${dataSource.stringItem("id")}_create_all.sql").apply {
            if (!parentFile.exists()) parentFile.mkdirs()
            writeText(velocityEngine["vm/database/data_source/%s_create_all.sql.vm"].render(context))
            formatSql(dataSource.stringItem("databaseType"))
        }
    }
}