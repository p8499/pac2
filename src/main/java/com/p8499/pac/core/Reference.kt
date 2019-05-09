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

//data class Reference(
//        @JsonProperty val domestics: MutableList<String> = mutableListOf(),
//        @JsonProperty var foreignModule: String = "",
//        @JsonProperty val foreigns: MutableIterable<String> = mutableListOf()) {
//    override fun toString(): String = domestics.joinToString(",")
//}

class Reference(
        domestics: ObservableList<String> = FXCollections.emptyObservableList(),
        foreignModule: String = "",
        foreigns: ObservableList<String> = FXCollections.emptyObservableList()) {
    var domestics: ObservableList<String>
        @JsonSerialize get() = domesticsProperty.value
        @JsonDeserialize(`as` = Domestics::class) set(value) = run { domesticsProperty.value = value }
    var foreignModule: String
        @JsonSerialize get() = foreignModuleProperty.value
        @JsonDeserialize set(value) = run { foreignModuleProperty.value = value }
    var foreigns: ObservableList<String>
        @JsonSerialize get() = foreignsProperty.value
        @JsonDeserialize(`as` = Foreigns::class) set(value) = run { foreignsProperty.value = value }
    @JsonIgnore
    val domesticsProperty: ListProperty<String> = SimpleListProperty(domestics)
    @JsonIgnore
    val foreignModuleProperty: StringProperty = SimpleStringProperty(foreignModule)
    @JsonIgnore
    val foreignsProperty: ListProperty<String> = SimpleListProperty(foreigns)

    override fun toString(): String = domestics.joinToString(",")

    object Domestics : ObservableList<String> by FXCollections.observableArrayList()
    object Foreigns : ObservableList<String> by FXCollections.observableArrayList()
}
