package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList

class EnvHtml(
        baseUrl: String = "") {
    var baseUrl: String
        @JsonSerialize get() = baseUrlProperty.value
        @JsonDeserialize set(value) = run { baseUrlProperty.value = value }
    @JsonIgnore
    val baseUrlProperty: StringProperty = SimpleStringProperty(baseUrl)

    override fun toString(): String = "HTML Environment"
}
