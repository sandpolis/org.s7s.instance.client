//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.ui.main

import com.sandpolis.client.lifegem.ui.common.pane.CarouselPane
import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class MainView : View("Main") {

    val profiles: ObservableList<STDocument> = FXCollections.observableArrayList()

    val hostList = tableview(profiles) {
        column<STDocument, String>("") {
            ReadOnlyObjectWrapper("")
        }
    }

    override val root = borderpane {
        top {
            menubar {
                menu("Interface") {
                    item("List View", "Shortcut+L")
                    item("Graph View", "Shortcut+G")
                    separator()
                    item("Move to system tray") {
                        tooltip("The application will continue running in the background and the UI will be hidden.")
                    }
                }
                menu("Management")
                menu("Help") {
                    item("About")
                }
            }
        }
        center = CarouselPane(hostList).apply {

        }
    }
}