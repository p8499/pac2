package com.p8499.pac.controller

import com.p8499.pac.core.Fields
import com.p8499.pac.core.Module
import com.p8499.pac.core.Unique
import com.p8499.pac.treeItem.ModuleTreeItem
import com.p8499.pac.treeItem.refresh
import javafx.beans.property.ObjectProperty
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.*

class UniqueController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var itemCandidates: ListView<String>
    @FXML private lateinit var items: ListView<String>
    @FXML private lateinit var key: CheckBox
    @FXML private lateinit var serial: CheckBox
    val isItemsValid: Boolean get() = true
    val isKeyValid: Boolean get() = true
    val isSerialValid: Boolean get() = true
    val fields: Fields get() = ((treeItem.parent.parent as ModuleTreeItem).value as Module).fields as Fields
    val core: Unique get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        itemCandidates.selectionModel.selectionMode = SelectionMode.MULTIPLE
        itemCandidates.items = FXCollections.emptyObservableList()
        items.selectionModel.selectionMode = SelectionMode.MULTIPLE
        itemCandidates.items = FXCollections.emptyObservableList()
        items.itemsProperty().addListener { observable, oldValue, newValue ->
            items.isValid = isItemsValid
            if (core.items != newValue) {
                treeItem.refresh()
                core.items = newValue
                isModified = true
            }
            itemCandidates.items = FXCollections.observableList(fields.filter { "table" == it.source && !items.items.contains(it.databaseColumn) }.map { it.databaseColumn })
        }
        key.text = null
        key.selectedProperty().addListener { observable, oldValue, newValue ->
            key.isValid = isKeyValid
            if (core.key != newValue) {
                core.key = newValue
                isModified = true
            }
        }
        serial.text = null
        serial.selectedProperty().addListener { observable, oldValue, newValue ->
            serial.isValid = isSerialValid
            if (core.serial != newValue) {
                core.serial = newValue
                isModified = true
            }
        }
    }

    override fun scenarized() {
        itemCandidates.items = FXCollections.observableList(fields.filter { "table" == it.source }.map { it.databaseColumn })
        items.items = core.items
        key.isSelected = core.key
        serial.isSelected = core.serial
    }

    @FXML
    fun onItemSelectClick() {
        items.items = FXCollections.observableList(items.items + itemCandidates.selectionModel.selectedItems)
    }

    @FXML
    fun onItemDeselectClick() {
        items.items = FXCollections.observableList(items.items - items.selectionModel.selectedItems)
    }
}