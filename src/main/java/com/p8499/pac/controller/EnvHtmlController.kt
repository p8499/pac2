package com.p8499.pac.controller

import com.p8499.pac.core.EnvHtml
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

class EnvHtmlController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var baseUrl: TextField
    val isBaseUrlValid: Boolean get() = baseUrl.text.isNotEmpty()
    val core: EnvHtml get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        baseUrl.text = null
        baseUrl.textProperty().addListener { observable, oldValue, newValue ->
            baseUrl.isValid = isBaseUrlValid
            if (core.baseUrl != newValue) {
                core.baseUrl = newValue
                isModified = true
            }
        }
    }

    override fun scenarized() {
        baseUrl.text = core.baseUrl
    }
}