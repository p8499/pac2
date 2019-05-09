package com.p8499.pac.controller

import com.p8499.pac.core.EnvAndroid
import javafx.beans.property.ObjectProperty
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TextField
import javafx.scene.control.TreeItem
import javafx.scene.layout.AnchorPane
import java.net.URL
import java.util.*

class EnvAndroidController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var app: TextField
    @FXML private lateinit var `package`: TextField
    val isAppValid: Boolean get() = app.text.matches("""[a-zA-Z_][a-zA-Z0-9_]*""".toRegex())
    val isPackageValid: Boolean get() = `package`.text.matches("""([a-z_][a-z0-9_]*(\.[a-z_][a-z0-9_]*)*)""".toRegex())
    val core: EnvAndroid get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        app.text = null
        app.textProperty().addListener { observable, oldValue, newValue ->
            app.isValid = isAppValid
            if (core.app != newValue) {
                core.app = newValue
                isModified = true
            }
        }
        `package`.text = null
        `package`.textProperty().addListener { observable, oldValue, newValue ->
            `package`.isValid = isPackageValid
            if (core.`package` != newValue) {
                core.`package` = newValue
                isModified = true
            }
        }
    }

    override fun scenarized() {
        app.text = core.app
        `package`.text = core.`package`
    }
}