package com.p8499.pac.formatter

import com.greenbird.xml.prettyprinter.PrettyPrinter
import com.greenbird.xml.prettyprinter.PrettyPrinterFactory
import java.io.File

private var printer: PrettyPrinter = PrettyPrinterFactory.newInstance().apply {
    isXmlDeclaration = true
    isIgnoreWhitespace = true
}.newPrettyPrinter()

fun File.formatXml() {
    val result = StringBuilder()
    printer.process(readText(), result)
    writeText(result.toString())
}