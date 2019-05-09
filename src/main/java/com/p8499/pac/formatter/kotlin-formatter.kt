package com.p8499.pac.formatter

import com.github.shyiko.ktlint.Main
import java.io.File

fun File.formatKotlin() = Main.main(arrayOf("-F", absolutePath))
