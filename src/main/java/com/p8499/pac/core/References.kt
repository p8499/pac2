package com.p8499.pac.core

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class References : ObservableList<Reference> by FXCollections.observableArrayList() {
    override fun toString(): String = "References"
}
