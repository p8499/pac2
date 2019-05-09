package com.p8499.pac.controller

import com.p8499.pac.core.DataSource
import com.p8499.pac.treeItem.refresh
import javafx.beans.property.ObjectProperty
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.control.TreeItem
import javafx.scene.layout.AnchorPane
import java.net.URL
import java.util.*

class DataSourceController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var id: TextField
    @FXML private lateinit var databaseType: ComboBox<String>
    @FXML private lateinit var url: TextField
    @FXML private lateinit var userName: TextField
    @FXML private lateinit var password: TextField
    val isIdValid: Boolean get() = id.text.matches("""[a-zA-Z_][a-zA-Z0-9_]*""".toRegex())
    val isDatabaseTypeValid: Boolean get() = !databaseType.selectionModel.isEmpty
    val isUrlValid: Boolean get() = true
    val isUserNameValid: Boolean get() = true
    val isPasswordValid: Boolean get() = true
    val core: DataSource get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        id.text = null
        id.textProperty().addListener { observable, oldValue, newValue ->
            id.isValid = isIdValid
            if (core.id != newValue) {
                treeItem.refresh()
                core.id = newValue
                isModified = true
            }
        }
        databaseType.selectionModel.clearSelection()
        databaseType.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            databaseType.isValid = isDatabaseTypeValid
            if (core.databaseType != newValue) {
                core.databaseType = newValue
                isModified = true
            }
        }
        url.text = null
        url.textProperty().addListener { observable, oldValue, newValue ->
            url.isValid = isUrlValid
            if (core.url != newValue) {
                core.url = newValue
                isModified = true
            }
        }
        userName.text = null
        userName.textProperty().addListener { observable, oldValue, newValue ->
            userName.isValid = isUserNameValid
            if (core.userName != newValue) {
                core.userName = newValue
                isModified = true
            }
        }
        password.text = null
        password.textProperty().addListener { observable, oldValue, newValue ->
            password.isValid = isPasswordValid
            if (core.password != newValue) {
                core.password = newValue
                isModified = true
            }
        }
    }

    override fun scenarized() {
        id.text = core.id
        databaseType.selectionModel.select(core.databaseType)
        url.text = core.url
        userName.text = core.userName
        password.text = core.password
    }
}