package com.p8499.pac.controller

import com.p8499.pac.core.Value
import com.p8499.pac.treeItem.refresh
import javafx.beans.property.ObjectProperty
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.*

class ValueController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var value: TextField
    @FXML private lateinit var code: TextField
    @FXML private lateinit var label: TextField
    val isValueValid: Boolean get() = true
    val isCodeValid: Boolean get() = true
    val isLabelValid: Boolean get() = true
    val core: Value get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        value.text = null
        value.textProperty().addListener { observable, oldValue, newValue ->
            value.isValid = isValueValid
            if (core.value != newValue) {
                treeItem.refresh()
                core.value = newValue
                isModified = true
            }
        }
        code.text = null
        code.textProperty().addListener { observable, oldValue, newValue ->
            code.isValid = isCodeValid
            if (core.code != newValue) {
                core.code = newValue
                isModified = true
            }
        }
        label.text = null
        label.textProperty().addListener { observable, oldValue, newValue ->
            label.isValid = isLabelValid
            if (core.label != newValue) {
                core.label = newValue
                isModified = true
            }
        }
    }

    override fun scenarized() {
        value.text = core.value
        code.text = core.code
        label.text = core.label
    }
}