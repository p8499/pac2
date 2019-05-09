package com.p8499.pac.treeItem

import com.p8499.pac.core.Value
import javafx.scene.control.TreeItem

class ValueTreeItem(value: Value, val parentPath: String) : TreeItem<Any>(value), PathOf {
    override val path get() = "$parentPath[${parent.children.indexOf(this) + 1}]"
}