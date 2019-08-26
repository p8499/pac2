package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList

//data class Field(
//        @JsonProperty var source: String = "",
//        @JsonProperty var databaseColumn: String = "",
//        @JsonProperty var description: String = "",
//        @JsonProperty var notNull: Boolean = false,
//        @JsonProperty var javaType: String = "",
//        @JsonProperty var stringLength: Int = 0,
//        @JsonProperty var integerLength: Int = 0,
//        @JsonProperty var fractionLength: Int = 0,
//        @JsonProperty var defaultValue: String = "",
//        @JsonProperty val values: Values = Values()) {
//    override fun toString(): String = databaseColumn
//}
class Field(
        source: String = "",
        databaseColumn: String = "",
        description: String = "",
        notNull: Boolean = false,
        javaType: String = "",
        stringLength: Int = 0,
        integerLength: Int = 0,
        fractionLength: Int = 0,
        defaultValue: String = "",
        datePrecision: String = "",
        values: ObservableList<Value> = FXCollections.emptyObservableList()) {
    var source: String
        @JsonSerialize get() = sourceProperty.value
        @JsonDeserialize set(value) = run { sourceProperty.value = value }
    var databaseColumn: String
        @JsonSerialize get() = databaseColumnProperty.value
        @JsonDeserialize set(value) = run { databaseColumnProperty.value = value }
    var description: String
        @JsonSerialize get() = descriptionProperty.value
        @JsonDeserialize set(value) = run { descriptionProperty.value = value }
    var notNull: Boolean
        @JsonSerialize get() = notNullProperty.value
        @JsonDeserialize set(value) = run { notNullProperty.value = value }
    var javaType: String
        @JsonSerialize get() = javaTypeProperty.value
        @JsonDeserialize set(value) = run { javaTypeProperty.value = value }
    var stringLength: Int
        @JsonSerialize get() = stringLengthProperty.value
        @JsonDeserialize set(value) = run { stringLengthProperty.value = value }
    var integerLength: Int
        @JsonSerialize get() = integerLengthProperty.value
        @JsonDeserialize set(value) = run { integerLengthProperty.value = value }
    var fractionLength: Int
        @JsonSerialize get() = fractionLengthProperty.value
        @JsonDeserialize set(value) = run { fractionLengthProperty.value = value }
    var defaultValue: String
        @JsonSerialize get() = defaultValueProperty.value
        @JsonDeserialize set(value) = run { defaultValueProperty.value = value }
    var datePrecision: String
        @JsonSerialize get() = datePrecisionProperty.value
        @JsonDeserialize set(value) = run { datePrecisionProperty.value = value }
    var values: ObservableList<Value>
        @JsonSerialize get() = valuesProperty.value
        @JsonDeserialize(`as` = Values::class) set(value) = run { valuesProperty.value = value }
    @JsonIgnore
    val sourceProperty: StringProperty = SimpleStringProperty(source)
    @JsonIgnore
    val databaseColumnProperty: StringProperty = SimpleStringProperty(databaseColumn)
    @JsonIgnore
    val descriptionProperty: StringProperty = SimpleStringProperty(description)
    @JsonIgnore
    val notNullProperty: BooleanProperty = SimpleBooleanProperty(notNull)
    @JsonIgnore
    val javaTypeProperty: StringProperty = SimpleStringProperty(javaType)
    @JsonIgnore
    val stringLengthProperty: IntegerProperty = SimpleIntegerProperty(stringLength)
    @JsonIgnore
    val integerLengthProperty: IntegerProperty = SimpleIntegerProperty(integerLength)
    @JsonIgnore
    val fractionLengthProperty: IntegerProperty = SimpleIntegerProperty(fractionLength)
    @JsonIgnore
    val defaultValueProperty: StringProperty = SimpleStringProperty(defaultValue)
    @JsonIgnore
    val datePrecisionProperty: StringProperty = SimpleStringProperty(datePrecision)
    @JsonIgnore
    val valuesProperty: ListProperty<Value> = SimpleListProperty(values)

    override fun toString(): String = databaseColumn
}