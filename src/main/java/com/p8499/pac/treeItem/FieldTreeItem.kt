package com.p8499.pac.treeItem

import com.p8499.pac.core.Field
import javafx.scene.control.TreeItem

class FieldTreeItem(field: Field, val parentPath: String) : TreeItem<Any>(field), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath[${parent.children.indexOf(this) + 1}]"
    override fun resetChildren() {
        children.setAll(ValuesTreeItem((value as Field).values, path))
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}