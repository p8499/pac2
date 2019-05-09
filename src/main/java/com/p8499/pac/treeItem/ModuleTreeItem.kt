package com.p8499.pac.treeItem

import com.p8499.pac.core.Module
import javafx.scene.control.TreeItem

class ModuleTreeItem(module: Module, val parentPath: String) : TreeItem<Any>(module), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath[${parent.children.indexOf(this) + 1}]"
    override fun resetChildren() {
        children.setAll(FieldsTreeItem((value as Module).fields, path), UniquesTreeItem((value as Module).uniques, path), ReferencesTreeItem((value as Module).references, path))
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}