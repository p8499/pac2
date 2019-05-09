package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class Value(
        value: String = "",
        code: String = "",
        label: String = "") {
    var value: String
        @JsonSerialize get() = valueProperty.value
        @JsonDeserialize set(value) = run { valueProperty.value = value }
    var code: String
        @JsonSerialize get() = codeProperty.value
        @JsonDeserialize set(value) = run { codeProperty.value = value }
    var label: String
        @JsonSerialize get() = labelProperty.value
        @JsonDeserialize set(value) = run { labelProperty.value = value }
    @JsonIgnore
    val valueProperty: StringProperty = SimpleStringProperty(value)
    @JsonIgnore
    val codeProperty: StringProperty = SimpleStringProperty(code)
    @JsonIgnore
    val labelProperty: StringProperty = SimpleStringProperty(label)

    override fun toString(): String = code
}