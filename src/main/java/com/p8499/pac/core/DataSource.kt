package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class DataSource(
        id: String = "",
        databaseType: String = "",
        url: String = "",
        userName: String = "",
        password: String = "") {
    var id: String
        @JsonSerialize get() = idProperty.value
        @JsonDeserialize set(value) = run { idProperty.value = value }
    var databaseType: String
        @JsonSerialize get() = databaseTypeProperty.value
        @JsonDeserialize set(value) = run { databaseTypeProperty.value = value }
    var url: String
        @JsonSerialize get() = urlProperty.value
        @JsonDeserialize set(value) = run { urlProperty.value = value }
    var userName: String
        @JsonSerialize get() = userNameProperty.value
        @JsonDeserialize set(value) = run { userNameProperty.value = value }
    var password: String
        @JsonSerialize get() = passwordProperty.value
        @JsonDeserialize set(value) = run { passwordProperty.value = value }
    @JsonIgnore
    val idProperty: StringProperty = SimpleStringProperty(id)
    @JsonIgnore
    val databaseTypeProperty: StringProperty = SimpleStringProperty(databaseType)
    @JsonIgnore
    val urlProperty: StringProperty = SimpleStringProperty(url)
    @JsonIgnore
    val userNameProperty: StringProperty = SimpleStringProperty(userName)
    @JsonIgnore
    val passwordProperty: StringProperty = SimpleStringProperty(password)

    override fun toString(): String = id
}