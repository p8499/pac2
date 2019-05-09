package com.p8499.pac.treeItem

import com.p8499.pac.core.EnvAndroid
import javafx.scene.control.TreeItem

class EnvAndroidTreeItem(envAndroid: EnvAndroid, val parentPath: String) : TreeItem<Any>(envAndroid), PathOf {
    override val path get() = "$parentPath/envAndroid"
}