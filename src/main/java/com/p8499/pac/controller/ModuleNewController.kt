package com.p8499.pac.controller

import com.p8499.pac.core.Module
import com.p8499.pac.core.Project
import javafx.beans.property.ObjectProperty
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.ComboBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent
import java.net.URL
import java.util.*

class ModuleNewController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var id: TextField
    @FXML private lateinit var description: TextField
    @FXML private lateinit var comment: TextArea
    @FXML private lateinit var dataSource: ComboBox<String>
    @FXML private lateinit var databaseTable: TextField
    @FXML private lateinit var databaseView: TextField
    @FXML private lateinit var alias: TextField
    @FXML private lateinit var path: TextField
    @FXML private lateinit var listPath: TextField
    @FXML private lateinit var attachmentPath: TextField
    val isIdValid: Boolean get() = id.text.matches("""[_]*[a-z][a-z0-9_]*""".toRegex())
    val isDescriptionValid: Boolean get() = true
    val isCommentValid: Boolean get() = true
    val isDataSourceValid: Boolean get() = !dataSource.selectionModel.isEmpty
    val isDatabaseTableValid: Boolean get() = databaseTable.text.matches("""[A-Z_][A-Z0-9_]*""".toRegex())
    val isDatabaseViewValid: Boolean get() = databaseView.text.matches("""[A-Z_][A-Z0-9_]*""".toRegex())
    val isAliasValid: Boolean get() = alias.text.matches("""[A-Z_][a-zA-Z0-9_]*""".toRegex())
    val isPathValid: Boolean get() = !path.text.startsWith("/") && !path.text.endsWith("/")
    val isListPathValid: Boolean get() = !listPath.text.startsWith("/") && !listPath.text.endsWith("/")
    val isAttachmentPathValid: Boolean get() = !attachmentPath.text.startsWith("/") && !attachmentPath.text.endsWith("/")
    val core: Module = Module()
    val project: Project
        get() = stage.get<ObjectProperty<Project>>("project").value

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        id.text = null
        id.textProperty().addListener { observable, oldValue, newValue ->
            id.isValid = isIdValid
            if (core.id != newValue) {
                core.id = newValue
            }
        }
        description.text = null
        description.textProperty().addListener { observable, oldValue, newValue ->
            description.isValid = isDescriptionValid
            if (core.description != newValue) {
                core.description = newValue
            }
        }
        comment.text = null
        comment.textProperty().addListener { observable, oldValue, newValue ->
            comment.isValid = isCommentValid
            if (core.comment != newValue) {
                core.comment = newValue
            }
        }
        dataSource.selectionModel.clearSelection()
        dataSource.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            dataSource.isValid = isDataSourceValid
            if (core.dataSource != newValue) {
                core.dataSource = newValue
            }
        }
        databaseTable.text = null
        databaseTable.textProperty().addListener { observable, oldValue, newValue ->
            databaseTable.isValid = isDatabaseTableValid
            if (core.databaseTable != newValue) {
                core.databaseTable = newValue
            }
        }
        databaseView.text = null
        databaseView.textProperty().addListener { observable, oldValue, newValue ->
            databaseView.isValid = isDatabaseViewValid
            if (core.databaseView != newValue) {
                core.databaseView = newValue
            }
        }
        alias.text = null
        alias.textProperty().addListener { observable, oldValue, newValue ->
            alias.isValid = isAliasValid
            if (core.alias != newValue) {
                core.alias = newValue
            }
        }
        path.text = null
        path.textProperty().addListener { observable, oldValue, newValue ->
            path.isValid = isPathValid
            if (core.path != newValue) {
                core.path = newValue
            }
        }
        listPath.text = null
        listPath.textProperty().addListener { observable, oldValue, newValue ->
            listPath.isValid = isListPathValid
            if (core.listPath != newValue) {
                core.listPath = newValue
            }
        }
        attachmentPath.text = null
        attachmentPath.textProperty().addListener { observable, oldValue, newValue ->
            attachmentPath.isValid = isAttachmentPathValid
            if (core.attachmentPath != newValue) {
                core.attachmentPath = newValue
            }
        }
    }

    override fun stagized() {
        stage.setOnCloseRequest(this::onClose)
        dataSource.items = FXCollections.observableList(project.envJtee.dataSources.map { it.id })
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
