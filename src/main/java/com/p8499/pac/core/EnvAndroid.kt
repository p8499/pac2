package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class EnvAndroid(
        app: String = "",
        `package`: String = "") {
    var app: String
        @JsonSerialize get() = appProperty.value
        @JsonDeserialize set(value) = run { appProperty.value = value }
    var `package`: String
        @JsonSerialize get() = packageProperty.value
        @JsonDeserialize set(value) = run { packageProperty.value = value }
    @JsonIgnore
    val appProperty: StringProperty = SimpleStringProperty(app)
    @JsonIgnore
    val packageProperty: StringProperty = SimpleStringProperty(`package`)

    override fun toString(): String = "Android Environment"
}