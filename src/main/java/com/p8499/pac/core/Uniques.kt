package com.p8499.pac.core

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class Uniques : ObservableList<Unique> by FXCollections.observableArrayList() {
    override fun toString(): String = "Uniques"
}
