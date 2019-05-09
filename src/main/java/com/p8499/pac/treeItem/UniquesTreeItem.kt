package com.p8499.pac.treeItem

import com.p8499.pac.core.Unique
import javafx.scene.control.TreeItem

class UniquesTreeItem(uniques: List<Unique>, val parentPath: String) : TreeItem<Any>(uniques), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/uniques"
    override fun resetChildren() {
        children.setAll((value as List<*>)
                .map { it as Unique }
                .mapIndexed { index, unique -> UniqueTreeItem(unique, path) })
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}