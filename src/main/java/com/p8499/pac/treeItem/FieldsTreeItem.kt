package com.p8499.pac.treeItem

import com.p8499.pac.core.Field
import javafx.scene.control.TreeItem

class FieldsTreeItem(fields: List<Field>, val parentPath: String) : TreeItem<Any>(fields), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/fields"
    override fun resetChildren() {
        children.setAll((value as List<*>)
                .map { it as Field }
                .mapIndexed { index, field -> FieldTreeItem(field, path) })
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}