package com.p8499.pac.controller

import com.p8499.pac.core.*
import com.p8499.pac.readFile
import com.p8499.pac.readResource
import com.p8499.pac.treeItem.ProjectTreeItem
import com.p8499.pac.writeFile
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.BorderPane
import javafx.stage.FileChooser
import java.io.File
import java.net.URL
import java.util.*

class ApplicationController : Controller() {
    override val root: Node get() = border
    @FXML private lateinit var border: BorderPane
    @FXML private lateinit var tree: TreeView<*>
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        super.initialize(location, resources)
        tree.selectionModel.selectedItemProperty().addListener { observable, oldValue, newValue -> showContent(newValue) }
    }

    override fun stagized() {
        stage["project"] = SimpleObjectProperty<Project>(Project()).apply {
            addListener { observable, oldValue, newValue -> tree.root = ProjectTreeItem(newValue).also { it.resetChildren() } }
        }
        stage["projectPath"] = SimpleObjectProperty<String>("").apply {
            addListener { observable, oldValue, newValue -> updateTitle() }
        }
        stage["isModified"] = SimpleObjectProperty<Boolean>(false).apply {
            addListener { observable, oldValue, newValue -> updateTitle() }
        }
        updateTitle()
        onEmptyClick()
    }

    @FXML
    fun onEmptyClick() = open(readResource("empty.json"))

    @FXML
    fun onSampleClick() = open(readResource("sales.json"))

    @FXML
    fun onOpenClick() {
        if (isModified) {
            //先保存再打开
            val confirm = confirm(stage, "Save?")
            if (confirm == true) {
                //要保存
                if (onSaveClick()) open()
            } else if (confirm == false) {
                //不保存
                open()
            }
        } else {
            //直接打开
            open()
        }
    }


    @FXML
    fun onSaveClick(): Boolean {
        val path = (if (projectPath.isNotEmpty()) projectPath else outputFile()?.absolutePath)
        return if (path != null) {
            save(path)
            true
        } else false
    }

    @FXML
    fun onSaveAsClick(): Boolean {
        val path = outputFile()?.absolutePath
        return if (path != null) {
            save(path)
            true
        } else false
    }

    private fun outputFile(): File? {
        val projectFile = File(projectPath)
        return FileChooser().let {
            it.initialDirectory = projectFile.parentFile
            it.initialFileName = projectFile.name ?: project?.name ?: ""
            it.extensionFilters.addAll(
                    FileChooser.ExtensionFilter("JSON file", "*.json"),
                    FileChooser.ExtensionFilter("Normal text file", "*.txt"),
                    FileChooser.ExtensionFilter("All types", "*.*"))
            it.showSaveDialog(stage)
        }
    }

    private fun inputFile(): File? {
        val projectFile = File(projectPath)
        return FileChooser().let {
            it.initialDirectory = projectFile.parentFile
            it.extensionFilters.addAll(
                    FileChooser.ExtensionFilter("JSON file", "*.json"),
                    FileChooser.ExtensionFilter("Normal text file", "*.txt"),
                    FileChooser.ExtensionFilter("All types", "*.*"))
            it.showOpenDialog(stage)
        }
    }

    private fun open() {
        inputFile()?.let { open(readFile(it), it.absolutePath) }
    }

    private fun open(prj: Project, prjPath: String = "") {
        project = prj
        projectPath = prjPath
        isModified = false
        tree.selectionModel.clearSelection()
    }

    private fun save(path: String) = project?.let {
        writeFile(it, path)
        projectPath = path
        isModified = false
    }

    private fun showContent(treeItem: TreeItem<*>?) {
        val core = treeItem?.value
        border.center = when (core) {
            is Project -> "vm/project.fxml"
            is EnvJtee -> "vm/envJtee.fxml"
            is DataSources -> "vm/dataSources.fxml"
            is DataSource -> "vm/dataSource.fxml"
            is EnvAndroid -> "vm/envAndroid.fxml"
            is Modules -> "vm/modules.fxml"
            is Module -> "vm/module.fxml"
            is Fields -> "vm/fields.fxml"
            is Field -> "vm/field.fxml"
            is Values -> "vm/values.fxml"
            is Value -> "vm/value.fxml"
            is Uniques -> "vm/uniques.fxml"
            is Unique -> "vm/unique.fxml"
            is References -> "vm/references.fxml"
            is Reference -> "vm/reference.fxml"
            else -> null
        }?.let {
            scene["core"] = core
            scene["treeItem"] = treeItem
            load(it)
        }
    }

    private fun updateTitle() {
        stage.title = String.format("%s%s - PAC Workbench", if (isModified) "*" else "", if (projectPath.isNotEmpty()) projectPath else "Untitled")
    }

    var project: Project?
        get() = stage.get<ObjectProperty<Project>>("project").value
        set(value) = run { stage.get<ObjectProperty<Project>>("project").value = value }
    var projectPath: String
        get() = stage.get<ObjectProperty<String>>("projectPath").value
        set(value) = run { stage.get<ObjectProperty<String>>("projectPath").value = value }
    var isModified: Boolean
        get() = stage.get<ObjectProperty<Boolean>>("isModified").value
        set(value) = run { stage.get<ObjectProperty<Boolean>>("isModified").value = value }
}