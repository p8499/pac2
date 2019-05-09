package com.p8499.pac.controller

import com.p8499.pac.core.Fields
import com.p8499.pac.core.Unique
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.CheckBox
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent
import java.net.URL
import java.util.*

class UniqueNewController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var itemCandidates: ListView<String>
    @FXML private lateinit var items: ListView<String>
    @FXML private lateinit var key: CheckBox
    @FXML private lateinit var serial: CheckBox
    val isItemsValid: Boolean get() = true
    val isKeyValid: Boolean get() = true
    val isSerialValid: Boolean get() = true
    val fields: Fields get() = stage["fields"]
    val core: Unique = Unique()
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        itemCandidates.selectionModel.selectionMode = SelectionMode.MULTIPLE
        itemCandidates.items = FXCollections.emptyObservableList()
        items.selectionModel.selectionMode = SelectionMode.MULTIPLE
        items.items = FXCollections.emptyObservableList()
        items.itemsProperty().addListener { observable, oldValue, newValue ->
            items.isValid = isItemsValid
            if (core.items != newValue) {
                core.items = newValue
            }
            itemCandidates.items = FXCollections.observableList(fields.filter { "table" == it.source && !items.items.contains(it.databaseColumn) }.map { it.databaseColumn })
        }
        key.text = null
        key.selectedProperty().addListener { observable, oldValue, newValue ->
            key.isValid = isKeyValid
            if (core.key != newValue) {
                core.key = newValue
            }
        }
        serial.text = null
        serial.selectedProperty().addListener { observable, oldValue, newValue ->
            serial.isValid = isSerialValid
            if (core.serial != newValue) {
                core.serial = newValue
            }
        }
    }

    override fun stagized() {
        stage.setOnCloseRequest(this::onClose)
        itemCandidates.items = FXCollections.observableList(fields.filter { "table" == it.source }.map { it.databaseColumn })
    }

    @FXML
    fun onItemSelectClick() {
        items.items = FXCollections.observableList(items.items + itemCandidates.selectionModel.selectedItems)
    }

    @FXML
    fun onItemDeselectClick() {
        items.items = FXCollections.observableList(items.items - items.selectionModel.selectedItems)
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

