package com.p8499.pac.formatter

import com.google.googlejavaformat.java.Formatter
import java.io.File

private val formatter: Formatter = Formatter()

fun File.formatJava() = writeText(formatter.formatSource(readText()))
