package com.p8499.pac.treeItem

import com.p8499.pac.core.Module
import javafx.scene.control.TreeItem

class ModulesTreeItem(modules: List<Module>, val parentPath: String) : TreeItem<Any>(modules), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/modules"
    override fun resetChildren() {
        children.setAll((value as List<*>)
                .map { it as Module }
                .mapIndexed { index, module -> ModuleTreeItem(module, path) })
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}