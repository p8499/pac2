package com.p8499.pac.treeItem

import com.p8499.pac.core.Project
import javafx.scene.control.TreeItem

class ProjectTreeItem(project: Project, val parentPath: String = ".") : TreeItem<Any>(project), PathOf, ResetChildrenOf {
    override val path get() = parentPath
    override fun resetChildren() {
        children.setAll(EnvJteeTreeItem((value as Project).envJtee, path), EnvAndroidTreeItem((value as Project).envAndroid, path), ModulesTreeItem((value as Project).modules, path))
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}
