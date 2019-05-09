package com.p8499.pac.treeItem

import com.p8499.pac.core.Unique
import javafx.scene.control.TreeItem

class UniqueTreeItem(unique: Unique, val parentPath: String) : TreeItem<Any>(unique), PathOf {
    override val path get() = "$parentPath[${parent.children.indexOf(this) + 1}]"
}