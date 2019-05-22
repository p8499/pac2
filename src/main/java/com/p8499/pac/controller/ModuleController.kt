package com.p8499.pac.controller

import com.p8499.pac.core.Fields
import com.p8499.pac.core.Module
import com.p8499.pac.core.References
import com.p8499.pac.core.Uniques
import com.p8499.pac.treeItem.find
import com.p8499.pac.treeItem.refresh
import com.p8499.pac.treeItem.select
import javafx.beans.property.ObjectProperty
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.*

class ModuleController : Controller() {
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
    @FXML private lateinit var attachmentPath: TextField
    val isIdValid: Boolean get() = id.text.matches("""[_]*[a-z][a-z0-9_]*""".toRegex())
    val isDescriptionValid: Boolean get() = true
    val isCommentValid: Boolean get() = true
    val isDataSourceValid: Boolean get() = !dataSource.selectionModel.isEmpty
    val isDatabaseTableValid: Boolean get() = databaseTable.text.matches("""[A-Z_][A-Z0-9_]*""".toRegex())
    val isDatabaseViewValid: Boolean get() = databaseView.text.matches("""[A-Z_][A-Z0-9_]*""".toRegex())
    val isAliasValid: Boolean get() = alias.text.matches("""[A-Z_][a-zA-Z0-9_]*""".toRegex())
    val isPathValid: Boolean get() = !path.text.startsWith("/") && path.text.endsWith("/")
    val isAttachmentPathValid: Boolean get() = !attachmentPath.text.startsWith("/") && attachmentPath.text.endsWith("/")
    val core: Module get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
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
        description.text = null
        description.textProperty().addListener { observable, oldValue, newValue ->
            description.isValid = isDescriptionValid
            if (core.description != newValue) {
                core.description = newValue
                isModified = true
            }
        }
        comment.text = null
        comment.textProperty().addListener { observable, oldValue, newValue ->
            comment.isValid = isCommentValid
            if (core.comment != newValue) {
                core.comment = newValue
                isModified = true
            }
        }
        dataSource.selectionModel.clearSelection()
        dataSource.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            dataSource.isValid = isDataSourceValid
            if (core.dataSource != newValue) {
                core.dataSource = newValue
                isModified = true
            }
        }
        databaseTable.text = null
        databaseTable.textProperty().addListener { observable, oldValue, newValue ->
            databaseTable.isValid = isDatabaseTableValid
            if (core.databaseTable != newValue) {
                core.databaseTable = newValue
                isModified = true
            }
        }
        databaseView.text = null
        databaseView.textProperty().addListener { observable, oldValue, newValue ->
            databaseView.isValid = isDatabaseViewValid
            if (core.databaseView != newValue) {
                core.databaseView = newValue
                isModified = true
            }
        }
        alias.text = null
        alias.textProperty().addListener { observable, oldValue, newValue ->
            alias.isValid = isAliasValid
            if (core.alias != newValue) {
                core.alias = newValue
                isModified = true
            }
        }
        path.text = null
        path.textProperty().addListener { observable, oldValue, newValue ->
            path.isValid = isPathValid
            if (core.path != newValue) {
                core.path = newValue
                isModified = true
            }
        }
        attachmentPath.text = null
        attachmentPath.textProperty().addListener { observable, oldValue, newValue ->
            attachmentPath.isValid = isAttachmentPathValid
            if (core.attachmentPath != newValue) {
                core.attachmentPath = newValue
                isModified = true
            }
        }
    }

    override fun scenarized() {
        id.text = core.id
        description.text = core.description
        comment.text = core.comment
        dataSource.selectionModel.select(core.dataSource)
        databaseTable.text = core.databaseTable
        databaseView.text = core.databaseView
        alias.text = core.alias
        path.text = core.path
        attachmentPath.text = core.attachmentPath
    }

    @FXML
    fun onFieldsClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(Fields::class.java))
    }

    @FXML
    fun onUniquesClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(Uniques::class.java))
    }

    @FXML
    fun onReferencesClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(References::class.java))
    }
}