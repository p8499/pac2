package com.p8499.pac.treeItem

import javafx.event.Event
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView

//abstract class PACTreeItem(value: Any) : TreeItem<Any>(value) {
//    fun refresh(): PACTreeItem = apply { Event.fireEvent(this, TreeItem.TreeModificationEvent(TreeItem.valueChangedEvent<Any>(), this, value)) }
//    fun resetChildren(): PACTreeItem = apply { onResetChildren() }
//    protected abstract fun onResetChildren()
//}

fun <T> TreeItem<T>.refresh() {
    Event.fireEvent(this, TreeItem.TreeModificationEvent(TreeItem.valueChangedEvent<T>(), this, value))
}

fun <T> TreeItem<T>.find(c: Class<out Any>): TreeItem<T>? {
    return children.find { c.isInstance(it.value) }
}

@Suppress("UNCHECKED_CAST")
fun TreeView<out Any>.select(treeItem: TreeItem<out Any>?) {
    (this as TreeView<Any>).selectionModel.select((treeItem as TreeItem<Any>))
}

interface PathOf {
    val path: String
}

interface ResetChildrenOf {
    fun resetChildren()
}
//
//fun <T> TreeItem<T>.refreshChildren(): TreeItem<T> = apply {
//    Event.fireEvent(this, TreeItem.TreeModificationEvent(TreeItem.childrenModificationEvent<T>(), this, value))
//}
//
//fun <T> TreeItem<T>.refresh(): TreeItem<T> = apply {
//    refreshLabel()
//    refreshChildren()
//}
