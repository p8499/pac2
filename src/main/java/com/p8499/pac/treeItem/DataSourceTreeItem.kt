package com.p8499.pac.treeItem

import com.p8499.pac.core.DataSource
import javafx.scene.control.TreeItem

class DataSourceTreeItem(dataSource: DataSource, val parentPath: String) : TreeItem<Any>(dataSource), PathOf {
    override val path get() = "$parentPath[${parent.children.indexOf(this) + 1}]"
}