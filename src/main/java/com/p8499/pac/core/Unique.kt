package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.BooleanProperty
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Unique(
        key: Boolean = false,
        serial: Boolean = false,
        items: ObservableList<String> = FXCollections.emptyObservableList()) {
    var key: Boolean
        @JsonSerialize get() = keyProperty.value
        @JsonDeserialize set(value) = run { keyProperty.value = value }
    var serial: Boolean
        @JsonSerialize get() = serialProperty.value
        @JsonDeserialize set(value) = run { serialProperty.value = value }
    var items: ObservableList<String>
        @JsonSerialize get() = itemsProperty.value
        @JsonDeserialize(`as` = Items::class) set(value) = run { itemsProperty.value = value }
    @JsonIgnore
    val keyProperty: BooleanProperty = SimpleBooleanProperty(key)
    @JsonIgnore
    val serialProperty: BooleanProperty = SimpleBooleanProperty(serial)
    @JsonIgnore
    val itemsProperty: ListProperty<String> = SimpleListProperty(items)

    override fun toString(): String = items.joinToString(",")
    object Items : ObservableList<String> by FXCollections.observableArrayList()
}


//data class Unique(
//        @JsonProperty var key: Boolean = false,
//        @JsonProperty = false,
//        @JsonProperty val items: MutableList<String> = mutableListOf()) {
//    override fun toString(): String = items.joinToString(",")
//}
