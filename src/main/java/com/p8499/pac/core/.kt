//package com.p8499.pac.core
//
//fun String.firstPath(): String = indexOf('/').takeIf { it > -1 }?.let { substring(0, it) } ?: this
//fun String.restPaths(): String = indexOf('/').takeIf { it > -1 }?.let { substring(it + 1) } ?: ""
