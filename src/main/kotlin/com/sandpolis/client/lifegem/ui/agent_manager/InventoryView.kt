//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.ui.agent_manager

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
