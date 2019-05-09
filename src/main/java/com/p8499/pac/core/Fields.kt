package com.p8499.pac.core

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Fields : ObservableList<Field> by FXCollections.observableArrayList() {
    override fun toString(): String = "Fields"
}
