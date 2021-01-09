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
import tornadofx.Fragment
import tornadofx.button
import tornadofx.titledpane

class PowerControlPrompt : Fragment() {

    val profile: STDocument by param()

    override val root = titledpane("Run power operation") {
        collapsibleProperty().set(false)
        button("Power off")
    }
}