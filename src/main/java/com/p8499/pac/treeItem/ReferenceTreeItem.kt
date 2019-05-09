package com.p8499.pac.treeItem

import com.p8499.pac.core.Reference
import javafx.scene.control.TreeItem

class ReferenceTreeItem(reference: Reference, val parentPath: String) : TreeItem<Any>(reference), PathOf {
    override val path get() = "$parentPath[${parent.children.indexOf(this) + 1}]"
}
