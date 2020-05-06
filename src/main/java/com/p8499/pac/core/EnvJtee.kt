package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.ObservableList

class EnvJtee(
        app: String = "",
        baseUrl: String = "",
        `package`: String = "",
        dataSources: ObservableList<DataSource> = DataSources()) {
    var app: String
        @JsonSerialize get() = appProperty.value
        @JsonDeserialize set(value) = run { appProperty.value = value }
    var baseUrl: String
        @JsonSerialize get() = baseUrlProperty.value
        @JsonDeserialize set(value) = run { baseUrlProperty.value = value }
    var `package`: String
        @JsonSerialize get() = packageProperty.value
        @JsonDeserialize set(value) = run { packageProperty.value = value }
    var dataSources: ObservableList<DataSource>
        @JsonSerialize get() = dataSourcesProperty.value
        @JsonDeserialize(`as` = DataSources::class) set(value) = run { dataSourcesProperty.value = value }
    @JsonIgnore
    val appProperty: StringProperty = SimpleStringProperty(app)
    @JsonIgnore
    val baseUrlProperty: StringProperty = SimpleStringProperty(baseUrl)
    @JsonIgnore
    val packageProperty: StringProperty = SimpleStringProperty(`package`)
    @JsonIgnore
    val dataSourcesProperty: ListProperty<DataSource> = SimpleListProperty(dataSources)

    override fun toString(): String = "J2EE Environment"
}


//class EnvJtee {
//    var app: String
//        @JsonProperty get() = appProperty.value
//        set(value) = run { appProperty.value = value }
//    var baseUrl: String
//        @JsonProperty get() = baseUrlProperty.value
//        set(value) = run { baseUrlProperty.value = value }
//    var packageBase: String
//        @JsonProperty get() = packageBaseProperty.value
//        set(value) = run { packageBaseProperty.value = value }
//    var packageBean: String
//        @JsonProperty get() = packageBeanProperty.value
//        set(value) = run { packageBeanProperty.value = value }
//    var packageMask: String
//        @JsonProperty get() = packageMaskProperty.value
//        set(value) = run { packageMaskProperty.value = value }
//    var packageMapper: String
//        @JsonProperty get() = packageMapperProperty.value
//        set(value) = run { packageMapperProperty.value = value }
//    var packageService: String
//        @JsonProperty get() = packageServiceProperty.value
//        set(value) = run { packageServiceProperty.value = value }
//    var packageControllerBase: String
//        @JsonProperty get() = packageControllerBaseProperty.value
//        set(value) = run { packageControllerBaseProperty.value = value }
//    var dataSources: DataSources
//        @JsonProperty get() = dataSourcesProperty.value
//        set(value) = run { dataSourcesProperty.value = value }
//    val appProperty: StringProperty = SimpleStringProperty("")
//    val baseUrlProperty: StringProperty = SimpleStringProperty("")
//    val packageBaseProperty: StringProperty = SimpleStringProperty("")
//    val packageBeanProperty: StringProperty = SimpleStringProperty("")
//    val packageMaskProperty: StringProperty = SimpleStringProperty("")
//    val packageMapperProperty: StringProperty = SimpleStringProperty("")
//    val packageServiceProperty: StringProperty = SimpleStringProperty("")
//    val packageControllerBaseProperty: StringProperty = SimpleStringProperty("")
//    val dataSourcesProperty: ObjectProperty<DataSources> = SimpleObjectProperty(DataSources())
//    override fun toString(): String = "J2EE Environment"
//}
