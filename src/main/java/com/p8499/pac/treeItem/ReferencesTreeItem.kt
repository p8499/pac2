package com.p8499.pac.treeItem

import com.p8499.pac.core.Reference
import javafx.scene.control.TreeItem

class ReferencesTreeItem(references: List<Reference>, val parentPath: String) : TreeItem<Any>(references), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/references"
    override fun resetChildren() {
        children.setAll((value as List<*>)
                .map { it as Reference }
                .mapIndexed { index, reference -> ReferenceTreeItem(reference, path) })
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}