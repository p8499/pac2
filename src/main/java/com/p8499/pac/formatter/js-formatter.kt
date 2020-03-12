package com.p8499.pac.formatter

import java.io.File
import java.nio.charset.Charset

fun File.formatSrc(lintFolder: File) {
    File(lintFolder, "src").also {
        copyRecursively(it, true)
        val process = ProcessBuilder("cmd.exe", "/c", """node_modules\.bin\eslint """, "src/**", "--fix").directory(lintFolder).start()
        val reader = process.inputStream.bufferedReader(Charset.forName("GB2312"))
        print(reader.readText())
        reader.close()
        if (process.waitFor() != 0)
            print("""
            |To create a Node.js project, use:
            | npm init
            | npm install typescript eslint
            | node_modules\.bin\eslint --init
            |""".trimMargin())
        process.destroy()
    }.copyRecursively(this, true)
//    val process = ProcessBuilder("cmd.exe", "/c", "js-beautify", "-r", absolutePath).start()
//    val reader = process.inputStream.bufferedReader(Charset.forName("GB2312"))
//    print(reader.readText())
//    reader.close()
//    if (process.waitFor() != 0)
//        println("js-beautify(https://github.com/beautify-web/js-beautify) Required.")
//    process.destroy()
}
