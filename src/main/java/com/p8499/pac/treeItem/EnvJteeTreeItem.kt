package com.p8499.pac.treeItem

import com.p8499.pac.core.EnvJtee
import javafx.scene.control.TreeItem

class EnvJteeTreeItem(envJtee: EnvJtee, val parentPath: String) : TreeItem<Any>(envJtee), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/envJtee"
    override fun resetChildren() {
        children.setAll(DataSourcesTreeItem((value as EnvJtee).dataSources, path))
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}