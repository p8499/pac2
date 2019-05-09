package com.p8499.pac.controller

import com.p8499.pac.core.Value
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent
import java.net.URL
import java.util.*

class ValueNewController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var value: TextField
    @FXML private lateinit var code: TextField
    @FXML private lateinit var label: TextField
    val isValueValid: Boolean get() = true
    val isCodeValid: Boolean get() = true
    val isLabelValid: Boolean get() = true
    val core: Value = Value()
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        value.text = null
        value.textProperty().addListener { observable, oldValue, newValue ->
            value.isValid = isValueValid
            if (core.value != newValue) {
                core.value = newValue
            }
        }
        code.text = null
        code.textProperty().addListener { observable, oldValue, newValue ->
            code.isValid = isCodeValid
            if (core.code != newValue) {
                core.code = newValue
            }
        }
        label.text = null
        label.textProperty().addListener { observable, oldValue, newValue ->
            label.isValid = isLabelValid
            if (core.label != newValue) {
                core.label = newValue
            }
        }
    }

    override fun stagized() {
        stage.setOnCloseRequest(this::onClose)
    }

    @FXML
    fun onSaveClick() {
        stage["result"] = core
        stage.close()
    }

    fun onClose(event: WindowEvent) {
        stage["result"] = null
    }
}