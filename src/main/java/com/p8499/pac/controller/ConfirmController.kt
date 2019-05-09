package com.p8499.pac.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent


class ConfirmController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var message: Label
    override fun stagized() {
        message.text = stage["message"]
        stage.setOnCloseRequest(this::onClose)
    }

    @FXML
    fun onYesClick(e: ActionEvent) {
        stage["result"] = true
        stage.close()
    }

    @FXML
    fun onNoClick(e: ActionEvent) {
        stage["result"] = false
        stage.close()
    }

    fun onClose(event: WindowEvent) {
        scene["result"] = null
    }
}
