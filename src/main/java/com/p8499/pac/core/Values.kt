package com.p8499.pac.core

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Values : ObservableList<Value> by FXCollections.observableArrayList() {
    override fun toString(): String = "Values"
}
