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

import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.client.lifegem.plugin.AgentViewExtension
import tornadofx.*

class InventoryView : AgentViewExtension("Inventory") {

    override val root = squeezebox {
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

    override fun nowVisible(profile: STDocument) {

    }

    override fun nowInvisible() {
        
    }
}
