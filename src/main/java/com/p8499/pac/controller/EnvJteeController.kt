package com.p8499.pac.controller

import com.p8499.pac.core.DataSources
import com.p8499.pac.core.EnvJtee
import com.p8499.pac.treeItem.find
import com.p8499.pac.treeItem.select
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

class EnvJteeController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var app: TextField
    @FXML private lateinit var baseUrl: TextField
    @FXML private lateinit var `package`: TextField
    val isAppValid: Boolean get() = app.text.matches("""[a-zA-Z_][a-zA-Z0-9_]*""".toRegex())
    val isBaseUrlValid: Boolean get() = baseUrl.text.isNotEmpty() && baseUrl.text.endsWith("/")
    val isPackageValid: Boolean get() = `package`.text.matches("""([a-z_][a-z0-9_]*(\.[a-z_][a-z0-9_]*)*)""".toRegex())
    val core: EnvJtee get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
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
        baseUrl.text = null
        baseUrl.textProperty().addListener { observable, oldValue, newValue ->
            baseUrl.isValid = isBaseUrlValid
            if (core.baseUrl != newValue) {
                core.baseUrl = newValue
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
        baseUrl.text = core.baseUrl
        `package`.text = core.`package`
    }

    @FXML
    fun onDataSourcesClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(DataSources::class.java))
    }
}