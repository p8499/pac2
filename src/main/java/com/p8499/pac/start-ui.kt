package com.p8499.pac

import com.p8499.pac.controller.scene
import javafx.application.Application
import javafx.stage.Stage

class PACApplication : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.scene = scene("fxml/application.fxml")
        primaryStage.isMaximized = true
        primaryStage.show()
    }
}

fun main(args: Array<String>) = Application.launch(PACApplication::class.java, *args)
