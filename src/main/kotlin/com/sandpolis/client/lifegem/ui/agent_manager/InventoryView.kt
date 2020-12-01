//============================================================================//
//                                                                            //
//                Copyright © 2015 - 2020 Subterranean Security               //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation at:                                //
//                                                                            //
//    https://mozilla.org/MPL/2.0                                             //
//                                                                            //
//=========================================================S A N D P O L I S==//

package com.sandpolis.client.lifegem.ui.agent_manager

import com.sandpolis.client.lifegem.ui.common.pane.ExtendPane
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.geometry.Side
import javafx.scene.control.TabPane
import javafx.scene.layout.Region
import tornadofx.*

class InventoryView : View() {

    override val root =
        squeezebox {
            fold("Metadata") {
                form {
                    fieldset {
                        field("First contact") {
                            label()
                        }
                        field("Last contact") {
                            label()
                        }
                    }
                }
            }
            fold("Settings") {}

            fold("Plugins") {}
        }
}
