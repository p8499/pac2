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

class Module(
        id: String = "",
        description: String = "",
        comment: String = "",
        dataSource: String = "",
        databaseTable: String = "",
        databaseView: String = "",
        alias: String = "",
        path: String = "",
        listPath: String = "",
        attachmentPath: String = "",
        fields: ObservableList<Field> = FXCollections.emptyObservableList(),
        uniques: ObservableList<Unique> = FXCollections.emptyObservableList(),
        references: ObservableList<Reference> = FXCollections.emptyObservableList()) {
    var id: String
        @JsonSerialize get() = idProperty.value
        @JsonDeserialize set(value) = run { idProperty.value = value }
    var description: String
        @JsonSerialize get() = descriptionProperty.value
        @JsonDeserialize set(value) = run { descriptionProperty.value = value }
    var comment: String
        @JsonSerialize get() = commentProperty.value
        @JsonDeserialize set(value) = run { commentProperty.value = value }
    var dataSource: String
        @JsonSerialize get() = dataSourceProperty.value
        @JsonDeserialize set(value) = run { dataSourceProperty.value = value }
    var databaseTable: String
        @JsonSerialize get() = databaseTableProperty.value
        @JsonDeserialize set(value) = run { databaseTableProperty.value = value }
    var databaseView: String
        @JsonSerialize get() = databaseViewProperty.value
        @JsonDeserialize set(value) = run { databaseViewProperty.value = value }
    var alias: String
        @JsonSerialize get() = aliasProperty.value
        @JsonDeserialize set(value) = run { aliasProperty.value = value }
    var path: String
        @JsonSerialize get() = pathProperty.value
        @JsonDeserialize set(value) = run { pathProperty.value = value }
    var listPath: String
        @JsonSerialize get() = listPathProperty.value
        @JsonDeserialize set(value) = run { listPathProperty.value = value }
    var attachmentPath: String
        @JsonSerialize get() = attachmentPathProperty.value
        @JsonDeserialize set(value) = run { attachmentPathProperty.value = value }
    var fields: ObservableList<Field>
        @JsonSerialize get() = fieldsProperty.value
        @JsonDeserialize(`as` = Fields::class) set(value) = run { fieldsProperty.value = value }
    var uniques: ObservableList<Unique>
        @JsonSerialize get() = uniquesProperty.value
        @JsonDeserialize(`as` = Uniques::class) set(value) = run { uniquesProperty.value = value }
    var references: ObservableList<Reference>
        @JsonSerialize get() = referencesProperty.value
        @JsonDeserialize(`as` = References::class) set(value) = run { referencesProperty.value = value }
    @JsonIgnore
    val idProperty: StringProperty = SimpleStringProperty(id)
    @JsonIgnore
    val descriptionProperty: StringProperty = SimpleStringProperty(description)
    @JsonIgnore
    val commentProperty: StringProperty = SimpleStringProperty(comment)
    @JsonIgnore
    val dataSourceProperty: StringProperty = SimpleStringProperty(dataSource)
    @JsonIgnore
    val databaseTableProperty: StringProperty = SimpleStringProperty(databaseTable)
    @JsonIgnore
    val databaseViewProperty: StringProperty = SimpleStringProperty(databaseView)
    @JsonIgnore
    val aliasProperty: StringProperty = SimpleStringProperty(alias)
    @JsonIgnore
    val pathProperty: StringProperty = SimpleStringProperty(path)
    @JsonIgnore
    val listPathProperty: StringProperty = SimpleStringProperty(listPath)
    @JsonIgnore
    val attachmentPathProperty: StringProperty = SimpleStringProperty(attachmentPath)
    @JsonIgnore
    val fieldsProperty: ListProperty<Field> = SimpleListProperty(fields)
    @JsonIgnore
    val uniquesProperty: ListProperty<Unique> = SimpleListProperty(uniques)
    @JsonIgnore
    val referencesProperty: ListProperty<Reference> = SimpleListProperty(references)

    override fun toString(): String = id
}