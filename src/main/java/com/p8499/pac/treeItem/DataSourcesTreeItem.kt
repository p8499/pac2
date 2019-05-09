package com.p8499.pac.treeItem

import com.p8499.pac.core.DataSource
import javafx.scene.control.TreeItem

class DataSourcesTreeItem(dataSources: List<DataSource>, val parentPath: String) : TreeItem<Any>(dataSources), PathOf, ResetChildrenOf {
    override val path get() = "$parentPath/dataSources"
    override fun resetChildren() {
        children.setAll((value as List<*>)
                .map { it as DataSource }
                .mapIndexed { index, dataSource -> DataSourceTreeItem(dataSource, path) })
        children.filterIsInstance(ResetChildrenOf::class.java).forEach { it.resetChildren() }
    }
}
