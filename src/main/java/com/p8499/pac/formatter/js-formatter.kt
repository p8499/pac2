package com.p8499.pac.formatter

import java.io.File
import java.nio.charset.Charset


fun File.formatJs() {
    val process = ProcessBuilder("cmd.exe", "/c", "js-beautify", "-r", absolutePath).start()
    val reader = process.inputStream.bufferedReader(Charset.forName("GB2312"))
    print(reader.readText())
    reader.close()
    if (process.waitFor() != 0)
        println("js-beautify(https://github.com/beautify-web/js-beautify) Required.")
    process.destroy()
}

