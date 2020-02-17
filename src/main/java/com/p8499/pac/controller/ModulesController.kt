package com.p8499.pac.controller

import com.p8499.pac.core.Module
import com.p8499.pac.move
import com.p8499.pac.treeItem.ResetChildrenOf
import com.p8499.pac.treeItem.select
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.input.ClipboardContent
import javafx.scene.input.DataFormat
import javafx.scene.input.TransferMode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import java.net.URL
import java.util.*

class ModulesController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var table: TableView<Module>
    @FXML private lateinit var columnId: TableColumn<Module, String>
    @FXML private lateinit var columnDescription: TableColumn<Module, String>
    @FXML private lateinit var columnDataSource: TableColumn<Module, String>
    @FXML private lateinit var columnDatabaseTable: TableColumn<Module, String>
    @FXML private lateinit var columnDatabaseView: TableColumn<Module, String>
    @FXML private lateinit var columnAlias: TableColumn<Module, String>
    val core: ObservableList<Module> get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        table.selectionModel.selectionMode = SelectionMode.MULTIPLE
        table.setRowFactory({ tableView ->
            val row = TableRow<Module>().apply {
                //double click
                setOnMouseClicked {
                    if (it.clickCount > 1 && !isEmpty) {
                        treeItem.isExpanded = true
                        tree.select(treeItem.children[index])
                    }
                }
                //drag and drop
                setOnDragDetected({ event ->
                    if (!isEmpty) {
                        val clipboardContent = ClipboardContent()
                        clipboardContent.put(DataFormat.PLAIN_TEXT, index)
                        val dragboard = startDragAndDrop(TransferMode.MOVE)
                        dragboard.dragView = snapshot(null, null)
                        dragboard.dragViewOffsetX = event.x
                        dragboard.dragViewOffsetY = event.y
                        dragboard.setContent(clipboardContent)
                        event.consume()
                    }
                })
                setOnDragOver { event ->
                    if (event.dragboard.hasContent(DataFormat.PLAIN_TEXT) && index != event.dragboard.getContent(DataFormat.PLAIN_TEXT) as Int) {
                        event.acceptTransferModes(TransferMode.MOVE)
                        event.consume()
                    }
                }
                setOnDragDropped { event ->
                    if (event.dragboard.hasContent(DataFormat.PLAIN_TEXT)) {
                        val draggedIndex = event.dragboard.getContent(DataFormat.PLAIN_TEXT) as Int
                        val dropIndex = if (isEmpty) tableView.items.size else index
                        if (draggedIndex != dropIndex - 1) {
                            val position = tableView.items.move(draggedIndex, dropIndex)
                            event.isDropCompleted = true
                            tableView.selectionModel.clearAndSelect(position)
                            (treeItem as ResetChildrenOf).resetChildren()
                            isModified = true
                        }
                        event.consume()
                    }
                }
            }
            row
        })
        columnId.setCellValueFactory { it.value.idProperty }
        columnDescription.setCellValueFactory { it.value.descriptionProperty }
        columnDataSource.setCellValueFactory { it.value.dataSourceProperty }
        columnDatabaseTable.setCellValueFactory { it.value.databaseTableProperty }
        columnDatabaseView.setCellValueFactory { it.value.databaseViewProperty }
        columnAlias.setCellValueFactory { it.value.aliasProperty }
    }

    override fun scenarized() {
        table.items = core
    }

    @FXML
    fun onDeleteClick() {
        val selected = table.selectionModel.selectedItems
        if (selected.isNotEmpty() && confirm(stage, String.format("Delete %d %s?", selected.size, if (selected.size > 1) "items" else "item")) == true) {
            core.removeAll(selected)
            (treeItem as ResetChildrenOf).resetChildren()
            isModified = true
        }
    }

    @FXML
    fun onAddClick() {
        val new = module(stage)
        if (new != null) {
            core.add(new)
            (treeItem as ResetChildrenOf).resetChildren()
            isModified = true
        }
    }
}