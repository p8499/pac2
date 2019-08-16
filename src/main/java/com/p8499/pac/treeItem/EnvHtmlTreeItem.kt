package com.p8499.pac.treeItem

import com.p8499.pac.core.EnvHtml
import javafx.scene.control.TreeItem

class EnvHtmlTreeItem(envHtml: EnvHtml, val parentPath: String) : TreeItem<Any>(envHtml), PathOf {
    override val path get() = "$parentPath/envHtml"
}