package com.p8499.pac

import com.p8499.pac.controller.scene
import com.p8499.pac.controller.set
import com.p8499.pac.generator.android_java.android_java
import com.p8499.pac.generator.database.database
import com.p8499.pac.generator.jmeter.jmeter
import com.p8499.pac.generator.node_js.node_js
import com.p8499.pac.generator.server_java.server_java
import javafx.application.Application
import javafx.stage.Stage
import jdk.nashorn.internal.runtime.ParserException
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import java.io.File

fun main(args: Array<String>) {
    val options = Options()
            .addOption("gd", "generate-database", true, "Generate database scripts.")
            .addOption("gm", "generate-jmeter", true, "Generate jmeter scripts.")
            .addOption("gs", "generate-server_java", true, "Generate server java code.")
            .addOption("gn", "generate-node_js", true, "Generate node js code.")
            .addOption("gn_eslint", "generate-node_js-eslint", true, "Specify the Node.js project which has ESLint installed to prettify the code.")
            .addOption("ga", "generate-android_java", true, "Generate android java code.")
            .addOption("u", "ui", false, "Launch ui.")
    try {
        val line = DefaultParser().parse(options, args)
        if (line.hasOption("gd"))
            database(readFileAsMap(line.args[0]), File(line.getOptionValue("gd")))
        if (line.hasOption("gm"))
            jmeter(readFileAsMap(line.args[0]), File(line.getOptionValue("gm")))
        if (line.hasOption("gs"))
            server_java(readFileAsMap(line.args[0]), File(line.getOptionValue("gs")))
        if (line.hasOption("gn"))
            if (line.hasOption("gn_eslint"))
                node_js(readFileAsMap(line.args[0]), File(line.getOptionValue("gn")), File(line.getOptionValue("gn_eslint")))
            else
                node_js(readFileAsMap(line.args[0]), File(line.getOptionValue("gn")))
        if (line.hasOption("ga"))
            android_java(readFileAsMap(line.args[0]), File(line.getOptionValue("ga")))
        if (line.hasOption("u"))
            Application.launch(PACApplication::class.java, line.args[0])
    } catch (e: ParserException) {
        System.err.println(e.message)
        System.exit(1)
    }
}

class PACApplication : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage["parameters"] = parameters.raw
        primaryStage.scene = scene("fxml/application.fxml")
        primaryStage.isMaximized = true
        primaryStage.show()
    }
}