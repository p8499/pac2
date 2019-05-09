package com.p8499.pac.core

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Modules : ObservableList<Module> by FXCollections.observableArrayList() {
    override fun toString(): String = "Modules"
}