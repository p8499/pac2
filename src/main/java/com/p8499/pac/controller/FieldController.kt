package com.p8499.pac.controller

import com.p8499.pac.core.Field
import com.p8499.pac.core.Values
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

class FieldController : Controller() {
    override val root: Node get() = anchor
    @FXML private lateinit var anchor: AnchorPane
    @FXML private lateinit var source: ComboBox<String>
    @FXML private lateinit var databaseColumn: TextField
    @FXML private lateinit var description: TextField
    @FXML private lateinit var notNull: CheckBox
    @FXML private lateinit var javaType: ComboBox<String>
    @FXML private lateinit var stringLength: TextField
    @FXML private lateinit var integerLength: TextField
    @FXML private lateinit var fractionLength: TextField
    @FXML private lateinit var defaultValue: TextField
    val isSourceValid: Boolean get() = !source.selectionModel.isEmpty
    val isDatabaseColumnValid: Boolean get() = databaseColumn.text.matches("""[a-z_][a-z0-9_]*""".toRegex())
    val isDescriptionValid: Boolean get() = true
    val isNotNullValid: Boolean get() = true
    val isJavaTypeValid: Boolean get() = !javaType.selectionModel.isEmpty
    val isStringLengthValid: Boolean get() = stringLength.isDisable || stringLength.text.matches("""[+-]?\d*""".toRegex())
    val isIntegerLengthValid: Boolean get() = integerLength.isDisable || integerLength.text.matches("""[+-]?\d*""".toRegex())
    val isFractionLengthValid: Boolean get() = fractionLength.isDisable || fractionLength.text.matches("""[+-]?\d*""".toRegex())
    val isDefaultValueValid: Boolean
        get() = when (javaType.selectionModel.selectedItem) {
            "Integer" -> defaultValue.text.isEmpty() || defaultValue.text.matches("""[+-]?\d*""".toRegex())
            "Double" -> defaultValue.text.isEmpty() || defaultValue.text.matches("""[+-]?(\d+(\.\d*)?|\.\d+)([eE]([+-]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?""".toRegex())
            else -> true
        }
    val core: Field get() = scene["core"]
    val treeItem: TreeItem<*> get() = scene["treeItem"]
    val tree: TreeView<*> get() = (scene.root as BorderPane).left as TreeView<*>
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        source.selectionModel.clearSelection()
        source.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            source.isValid = isSourceValid
            notNull.isDisable = source.selectionModel.selectedItem != "table"
            stringLength.isDisable = source.selectionModel.selectedItem != "table"
            integerLength.isDisable = source.selectionModel.selectedItem != "table"
            fractionLength.isDisable = source.selectionModel.selectedItem != "table"
            defaultValue.isDisable = source.selectionModel.selectedItem != "table"
            if (core.source != newValue) {
                core.source = newValue
                isModified = true
            }
        }
        databaseColumn.text = null
        databaseColumn.textProperty().addListener { observable, oldValue, newValue ->
            databaseColumn.isValid = isDatabaseColumnValid
            if (core.databaseColumn != newValue) {
                treeItem.refresh()
                core.databaseColumn = newValue
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
        notNull.text = null
        notNull.selectedProperty().addListener { observable, oldValue, newValue ->
            notNull.isValid = isNotNullValid
            if (core.notNull != newValue) {
                core.notNull = newValue
                isModified = true
            }
        }
        notNull.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) notNull.isSelected = false })
        javaType.selectionModel.clearSelection()
        javaType.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            javaType.isValid = isJavaTypeValid
            stringLength.isDisable = javaType.selectionModel.selectedItem != "String"
            integerLength.isDisable = javaType.selectionModel.selectedItem !in arrayOf("Integer", "Double")
            fractionLength.isDisable = javaType.selectionModel.selectedItem != "Double"
            defaultValue.isDisable = javaType.selectionModel.selectedItem !in arrayOf("Integer", "Double", "String")
            if (core.javaType != newValue) {
                core.javaType = newValue
                isModified = true
            }
        }
        stringLength.text = null
        stringLength.textProperty().addListener { observable, oldValue, newValue ->
            stringLength.isValid = isStringLengthValid
            newValue.toIntOrNull()?.let {
                if (core.stringLength != it) {
                    core.stringLength = it
                    isModified = true
                }
            }
        }
        stringLength.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) stringLength.text = "0" })
        integerLength.text = null
        integerLength.textProperty().addListener { observable, oldValue, newValue ->
            integerLength.isValid = isIntegerLengthValid
            newValue.toIntOrNull()?.let {
                if (core.integerLength != it) {
                    core.integerLength = it
                    isModified = true
                }
            }
        }
        integerLength.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) integerLength.text = "0" })
        fractionLength.text = null
        fractionLength.textProperty().addListener { observable, oldValue, newValue ->
            fractionLength.isValid = isFractionLengthValid
            newValue.toIntOrNull()?.let {
                if (core.fractionLength != it) {
                    core.fractionLength = it
                    isModified = true
                }
            }
        }
        fractionLength.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) fractionLength.text = "0" })
        defaultValue.text = null
        defaultValue.textProperty().addListener { observable, oldValue, newValue ->
            defaultValue.isValid = isDefaultValueValid
            if (core.defaultValue != newValue) {
                core.defaultValue = newValue
                isModified = true
            }
        }
        defaultValue.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) integerLength.text = "" })
    }

    override fun scenarized() {
        source.selectionModel.select(core.source)
        databaseColumn.text = core.databaseColumn
        description.text = core.description
        notNull.isSelected = core.notNull
        javaType.selectionModel.select(core.javaType)
        stringLength.text = core.stringLength.toString()
        integerLength.text = core.integerLength.toString()
        fractionLength.text = core.fractionLength.toString()
        defaultValue.text = core.defaultValue
    }

    @FXML
    fun onValuesClick() {
        treeItem.isExpanded = true
        tree.select(treeItem.find(Values::class.java))
    }
}