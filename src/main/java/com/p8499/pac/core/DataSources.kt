package com.p8499.pac.core

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class DataSources : ObservableList<DataSource> by FXCollections.observableArrayList() {
    override fun toString(): String = "DataSources"
}
