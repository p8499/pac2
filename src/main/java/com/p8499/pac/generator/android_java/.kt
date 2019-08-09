package com.p8499.pac.generator.android_java

import com.p8499.pac.generator.android_java.base.*
import com.p8499.pac.generator.android_java.module.bean
import com.p8499.pac.generator.android_java.module.mask
import com.p8499.pac.generator.android_java.module.stub
import java.io.File

fun android_java(project: Map<*, *>, folder: File) {
    androidManifest(project, folder)
    build(project, folder)
    strings(project, folder)
    cookieManager(project, folder)
    defaultDateFormatter(project, folder)
    filterConditionExpr(project, folder)
    filterDeserializer(project, folder)
    filterExpr(project, folder)
    filterLogicExpr(project, folder)
    filterOperandExpr(project, folder)
    filterSerializer(project, folder)
    orderByExpr(project, folder)
    orderByListExpr(project, folder)
    persistentCookieStore(project, folder)
    rangeExpr(project, folder)
    rangeListExpr(project, folder)
    retrofitFactory(project, folder)
    serializableCookie(project, folder)
    bean(project, folder)
    mask(project, folder)
    stub(project, folder)
}