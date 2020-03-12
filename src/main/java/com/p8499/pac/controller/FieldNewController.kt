package com.p8499.pac.controller

import com.p8499.pac.core.Field
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.CheckBox
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.WindowEvent
import java.net.URL
import java.util.*

class FieldNewController : Controller() {
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
    @FXML private lateinit var datePrecision: ComboBox<String>
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
    val isDatePrecisionValid: Boolean get() = datePrecision.isDisable || datePrecision.selectionModel.selectedItem != ""
    val core: Field = Field()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        source.selectionModel.clearSelection()
        source.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            source.isValid = isSourceValid
            notNull.isDisable = source.selectionModel.selectedItem != "table"
            defaultValue.isDisable = source.selectionModel.selectedItem != "table" || javaType.selectionModel.selectedItem !in arrayOf("Integer", "Double", "String")
            if (core.source != newValue) {
                core.source = newValue
            }
        }
        databaseColumn.text = null
        databaseColumn.textProperty().addListener { observable, oldValue, newValue ->
            databaseColumn.isValid = isDatabaseColumnValid
            if (core.databaseColumn != newValue) {
                core.databaseColumn = newValue
            }
        }
        description.text = null
        description.textProperty().addListener { observable, oldValue, newValue ->
            description.isValid = isDescriptionValid
            if (core.description != newValue) {
                core.description = newValue
            }
        }
        notNull.text = null
        notNull.selectedProperty().addListener { observable, oldValue, newValue ->
            notNull.isValid = isNotNullValid
            if (core.notNull != newValue) {
                core.notNull = newValue
            }
        }
        notNull.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) notNull.isSelected = false })
        javaType.selectionModel.clearSelection()
        javaType.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            javaType.isValid = isJavaTypeValid
            stringLength.isDisable = javaType.selectionModel.selectedItem != "String"
            integerLength.isDisable = javaType.selectionModel.selectedItem !in arrayOf("Integer", "Double")
            fractionLength.isDisable = javaType.selectionModel.selectedItem != "Double"
            defaultValue.isDisable = source.selectionModel.selectedItem != "table" || javaType.selectionModel.selectedItem !in arrayOf("Integer", "Double", "String")
            datePrecision.isDisable = javaType.selectionModel.selectedItem != "java.util.Date"
            if (core.javaType != newValue) {
                core.javaType = newValue
            }
        }
        stringLength.text = null
        stringLength.textProperty().addListener { observable, oldValue, newValue ->
            stringLength.isValid = isStringLengthValid
            newValue.toIntOrNull()?.let {
                if (core.stringLength != it) {
                    core.stringLength = it
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
                }
            }
        }
        fractionLength.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) fractionLength.text = "0" })
        defaultValue.text = null
        defaultValue.textProperty().addListener { observable, oldValue, newValue ->
            defaultValue.isValid = isDefaultValueValid
            if (core.defaultValue != newValue) {
                core.defaultValue = newValue
            }
        }
        defaultValue.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) integerLength.text = "" })
        datePrecision.selectionModel.clearSelection()
        datePrecision.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue ->
            datePrecision.isValid = isDatePrecisionValid
            if (core.datePrecision != newValue) {
                core.datePrecision = newValue
            }
        }
        datePrecision.disableProperty().addListener({ observable, oldValue, newValue -> if (newValue) datePrecision.selectionModel.select("") })
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
