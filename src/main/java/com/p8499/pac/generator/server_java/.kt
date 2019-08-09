package com.p8499.pac.generator.server_java

import com.p8499.pac.generator.server_java.base.*
import com.p8499.pac.generator.server_java.module.*
import java.io.File

fun server_java(project: Map<*, *>, folder: File) {
    build(project, folder)
    database(project, folder)
    log4j2(project, folder)
    mybatisConfig(project, folder)
    springContext(project, folder)
    web(project, folder)
    defaultDateFormatter(project, folder)
    filterConditionExpr(project, folder)
    filterDeserializer(project, folder)
    filterExpr(project, folder)
    filterLogicExpr(project, folder)
    filterOperandExpr(project, folder)
    filterSerializer(project, folder)
    orderByExpr(project, folder)
    orderByListExpr(project, folder)
    rangeExpr(project, folder)
    rangeListExpr(project, folder)
    bean(project, folder)
    mask(project, folder)
    mapper(project, folder)
    service(project, folder)
    controllerBase(project, folder)
}