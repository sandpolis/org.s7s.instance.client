//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.ui.server_manager

import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class ServerManagerView : View("Server Manager") {

    val controller: ServerManagerController by inject()

    val users: ObservableList<STDocument> = FXCollections.observableArrayList()
    val listeners: ObservableList<STDocument> = FXCollections.observableArrayList()
    val groups: ObservableList<STDocument> = FXCollections.observableArrayList()

    override  val root = drawer {
        item("Servers") {}
        item("Listeners") {
            borderpane {
                top =
                    buttonbar {
                        button("Add") {}

                        button("Delete") {}
                    }
                center =
                    tableview(listeners) {
                        column<STDocument, String>("Name") {
                            ReadOnlyObjectWrapper("")
                        }
                    }
            }
        }
        item("Users") {
            borderpane {
                top =
                    buttonbar {
                        button("Add") {}

                        button("Delete") {}
                    }
                center =
                    tableview(users) {
                        column<STDocument, String>("Username") {
                            ReadOnlyObjectWrapper("")
                        }
                        column<STDocument, String>("Password age") {
                            ReadOnlyObjectWrapper("")
                        }
                        column<STDocument, String>("Last login") {
                            ReadOnlyObjectWrapper("")
                        }
                        column<STDocument, String>("Login address") {
                            ReadOnlyObjectWrapper("")
                        }
                    }
            }
        }
        item("Agent Groups") {
            borderpane {
                top =
                    buttonbar {
                        button("Add") {}

                        button("Import") {}
                    }
                center =
                    tableview(groups) {
                        column<STDocument, String>("Name") {
                            ReadOnlyObjectWrapper("")
                        }
                        //readonlyColumn("", FxGroup::name) {
                        //    cellFormat { graphic = button("1") }
                        //}
                    }
            }
        }
    }
}
