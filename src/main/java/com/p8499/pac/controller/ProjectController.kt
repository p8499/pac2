package com.p8499.pac.controller

import com.p8499.pac.core.*
import com.p8499.pac.treeItem.find
import com.p8499.pac.treeItem.refresh
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

class ProjectController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var name: TextField
    val isNameValid: Boolean get() = name.text.matches("""[a-zA-Z_][a-zA-Z0-9_]*""".toRegex())
    val core: Project get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        name.text = null
        name.textProperty().addListener { observable, oldValue, newValue ->
            name.isValid = isNameValid
            if (core.name != newValue) {
                core.name = newValue
                treeItem.refresh()
                isModified = true
            }
        }
    }

    override fun scenarized() {
        name.text = core.name
    }

    @FXML
    fun onEnvJteeClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(EnvJtee::class.java))
    }

    @FXML
    fun onEnvHtmlClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(EnvHtml::class.java))
    }

    @FXML
    fun onEnvAndroidClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(EnvAndroid::class.java))
    }

    @FXML
    fun onModulesClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(Modules::class.java))
    }
}