package com.p8499.pac.treeItem

import com.p8499.pac.core.Value
import javafx.scene.control.TreeItem

class ValuesTreeItem(values: List<Value>, val parentPath: String) : TreeItem<Any>(values), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/values"
    override fun resetChildren() {
        children.setAll((value as List<*>)
                .map { it as Value }
                .mapIndexed { index, value -> ValueTreeItem(value, path) })
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}