package com.p8499.pac.controller

import com.p8499.pac.core.Fields
import com.p8499.pac.core.Modules
import com.p8499.pac.core.Reference
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.ComboBox
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent
import java.net.URL
import java.util.*

class ReferenceNewController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var domesticCandidates: ListView<String>
    @FXML private lateinit var domestics: ListView<String>
    @FXML private lateinit var foreignModule: ComboBox<String>
    @FXML private lateinit var foreignCandidates: ListView<String>
    @FXML private lateinit var foreigns: ListView<String>
    val isDomesticsValid: Boolean get() = true
    val isForeignModuleValid: Boolean get() = true
    val isForeignsValid: Boolean get() = true
    val fields: Fields get() = stage["fields"]
    val modules: Modules get() = stage["modules"]
    val core: Reference = Reference()
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        domesticCandidates.selectionModel.selectionMode = SelectionMode.MULTIPLE
        domesticCandidates.items = FXCollections.emptyObservableList()
        domestics.selectionModel.selectionMode = SelectionMode.MULTIPLE
        domestics.items = FXCollections.emptyObservableList()
        domestics.itemsProperty().addListener { observable, oldValue, newValue ->
            domestics.isValid = isDomesticsValid
            if (core.domestics != newValue) {
                core.domestics = newValue
            }
            domesticCandidates.items = FXCollections.observableList(fields.filter { "table" == it.source && !domestics.items.contains(it.databaseColumn) }.map { it.databaseColumn })
        }
        foreignModule.selectionModel.clearSelection()
        foreignModule.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            foreignModule.isValid = isForeignModuleValid
            if (core.foreignModule != newValue) {
                core.foreignModule = newValue
            }
            foreignCandidates.items = FXCollections.observableList(modules.find { it.alias == newValue }?.fields?.filter { "table" == it.source }?.map { it.databaseColumn })
            foreigns.items = FXCollections.emptyObservableList()
        }
        foreignCandidates.selectionModel.selectionMode = SelectionMode.MULTIPLE
        foreignCandidates.items = FXCollections.emptyObservableList()
        foreigns.selectionModel.selectionMode = SelectionMode.MULTIPLE
        foreigns.items = FXCollections.emptyObservableList()
        foreigns.itemsProperty().addListener { observable, oldValue, newValue ->
            foreigns.isValid = isForeignsValid
            if (core.foreigns != newValue) {
                core.foreigns = newValue
            }
            foreignCandidates.items = FXCollections.observableList(modules.find { it.alias == foreignModule.selectionModel.selectedItem }?.fields?.filter { "table" == it.source && !foreigns.items.contains(it.databaseColumn) }?.map { it.databaseColumn })
        }
    }

    override fun stagized() {
        stage.setOnCloseRequest(this::onClose)
        domesticCandidates.items = FXCollections.observableList(fields.filter { "table" == it.source }.map { it.databaseColumn })
        foreignModule.items = FXCollections.observableList(modules.map { it.alias })
    }

    @FXML
    fun onDomesticSelectClick() {
        domestics.items = FXCollections.observableList(domestics.items + domesticCandidates.selectionModel.selectedItems)
    }

    @FXML
    fun onDomesticDeselectClick() {
        domestics.items = FXCollections.observableList(domestics.items - domestics.selectionModel.selectedItems)
    }

    @FXML
    fun onForeignSelectClick() {
        foreigns.items = FXCollections.observableList(foreigns.items + foreignCandidates.selectionModel.selectedItems)
    }

    @FXML
    fun onForeignDeselectClick() {
        foreigns.items = FXCollections.observableList(foreigns.items - foreigns.selectionModel.selectedItems)
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