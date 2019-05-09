package com.p8499.pac.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Project(
        name: String = "",
        envJtee: EnvJtee = EnvJtee(),
        envAndroid: EnvAndroid = EnvAndroid(),
        modules: ObservableList<Module> = FXCollections.emptyObservableList()) {
    var name: String
        @JsonSerialize get() = nameProperty.value
        @JsonDeserialize set(value) = run { nameProperty.value = value }
    var envJtee: EnvJtee
        @JsonSerialize get() = envJteeProperty.value
        @JsonDeserialize set(value) = run { envJteeProperty.value = value }
    var envAndroid: EnvAndroid
        @JsonSerialize get() = envAndroidProperty.value
        @JsonDeserialize set(value) = run { envAndroidProperty.value = value }
    var modules: ObservableList<Module>
        @JsonSerialize get() = modulesProperty.value
        @JsonDeserialize(`as` = Modules::class) set(value) = run { modulesProperty.value = value }
    @JsonIgnore
    val nameProperty: StringProperty = SimpleStringProperty(name)
    @JsonIgnore
    val envJteeProperty: ObjectProperty<EnvJtee> = SimpleObjectProperty(envJtee)
    @JsonIgnore
    val envAndroidProperty: ObjectProperty<EnvAndroid> = SimpleObjectProperty(envAndroid)
    @JsonIgnore
    val modulesProperty: ListProperty<Module> = SimpleListProperty(modules)

    override fun toString(): String = name
}
//class  A<T>: JsonDeserializer<ObservableList<T>>() {
//    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): ObservableList<T> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}