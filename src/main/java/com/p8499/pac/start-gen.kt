package com.p8499.pac

import com.p8499.pac.generator.android_java.android_java
import com.p8499.pac.generator.database.database
import com.p8499.pac.generator.jmeter.jmeter
import com.p8499.pac.generator.server_java.server_java
import java.io.File


fun main(args: Array<String>) {
    //TODO cmd parameters
    jmeter(readFileAsMap("C:/Users/jdeuser/Desktop/sales/sales.json"), File("C:/Users/jdeuser/Desktop/sales/jmeter"))
    database(readFileAsMap("C:/Users/jdeuser/Desktop/sales/sales.json"), File("C:/Users/jdeuser/Desktop/sales/database"))
    server_java(readFileAsMap("C:/Users/jdeuser/Desktop/sales/sales.json"), File("C:/Users/jdeuser/Desktop/sales/server_java"))
    android_java(readFileAsMap("C:/Users/jdeuser/Desktop/sales/sales.json"), File("C:/Users/jdeuser/Desktop/sales/android_java"))
}