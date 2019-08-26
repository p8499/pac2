package com.p8499.pac.controller

import com.p8499.pac.core.*
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.stage.Modality
import javafx.stage.Stage
import java.net.URL
import java.util.*


abstract class Controller : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        root.sceneProperty().addListener { observable, oldValue, newValue ->
            newValue?.let {
                it.windowProperty().addListener { observable, oldValue, newValue -> (newValue as? Stage)?.let { stagized() } }
                scenarized()
            }
        }
    }

    open fun scenarized() {}
    open fun stagized() {}
    abstract val root: Node
    val scene: Scene get() = root.scene
    val stage: Stage get() = scene.window as Stage
}

inline operator fun <reified T> Stage.get(name: String): T = (userData as? Map<*, *> ?: mapOf<String, Any?>())[name] as T
operator fun Stage.set(name: String, value: Any?) = run { userData = (userData as? Map<*, *> ?: mapOf<String, Any?>()) + (name to value) }
inline operator fun <reified T> Scene.get(name: String): T = (userData as? Map<*, *> ?: mapOf<String, Any?>())[name] as T
operator fun Scene.set(name: String, value: Any?) = run { userData = (userData as? Map<*, *> ?: mapOf<String, Any?>()) + (name to value) }
var Region.isValid: Boolean
    get() = !styleClass.contains("error")
    set(value) {
        if (value) styleClass.removeAll(Collections.singleton("error"))
        else if (!styleClass.contains("error")) styleClass.add("error")
    }

fun load(vm: String): Parent {
    return FXMLLoader(Thread.currentThread().contextClassLoader.getResource(vm)).load()
}

fun scene(vm: String): Scene {
    return Scene(load(vm))
}

fun stage(owner: Stage? = null, modality: Modality = Modality.NONE): Stage = Stage().also {
    it.initModality(modality)
    it.initOwner(owner)
}

fun confirm(owner: Stage, message: String): Boolean? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage["message"] = message
    stage.scene = scene("fxml/confirm.fxml")
    stage.showAndWait()
    return stage["result"]
}

fun dataSource(owner: Stage): DataSource? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage.scene = scene("fxml/dataSource_new.fxml")
    stage.showAndWait()
    return stage["result"]
}

fun module(owner: Stage): Module? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage["project"] = owner["project"]//need context
    stage.scene = scene("fxml/module_new.fxml")
    stage.showAndWait()
    return stage["result"]
}

fun field(owner: Stage): Field? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage.scene = scene("fxml/field_new.fxml")
    stage.showAndWait()
    return stage["result"]
}

fun value(owner: Stage): Value? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage.scene = scene("fxml/value_new.fxml")
    stage.showAndWait()
    return stage["result"]
}

fun unique(owner: Stage, fields: Fields): Unique? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage["fields"] = fields
    stage.scene = scene("fxml/unique_new.fxml")
    stage.showAndWait()
    return stage["result"]
}

fun reference(owner: Stage, fields: Fields, modules: Modules): Reference? {
    val stage = stage(owner, Modality.WINDOW_MODAL)
    stage["fields"] = fields
    stage["modules"] = modules
    stage.scene = scene("fxml/reference_new.fxml")
    stage.showAndWait()
    return stage["result"]
}
