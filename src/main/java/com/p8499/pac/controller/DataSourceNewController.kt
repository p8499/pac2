package com.p8499.pac.controller

import com.p8499.pac.core.DataSource
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent
import java.net.URL
import java.util.*

class DataSourceNewController : Controller() {
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
    val core: DataSource = DataSource()
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        id.text = null
        id.textProperty().addListener { observable, oldValue, newValue ->
            id.isValid = isIdValid
            if (core.id != newValue) {
                core.id = newValue
            }
        }
        databaseType.selectionModel.clearSelection()
        databaseType.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            databaseType.isValid = isDatabaseTypeValid
            if (core.databaseType != newValue) {
                core.databaseType = newValue
            }
        }
        url.text = null
        url.textProperty().addListener { observable, oldValue, newValue ->
            url.isValid = isUrlValid
            if (core.url != newValue) {
                core.url = newValue
            }
        }
        userName.text = null
        userName.textProperty().addListener { observable, oldValue, newValue ->
            userName.isValid = isUserNameValid
            if (core.userName != newValue) {
                core.userName = newValue
            }
        }
        password.text = null
        password.textProperty().addListener { observable, oldValue, newValue ->
            password.isValid = isPasswordValid
            if (core.password != newValue) {
                core.password = newValue
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
