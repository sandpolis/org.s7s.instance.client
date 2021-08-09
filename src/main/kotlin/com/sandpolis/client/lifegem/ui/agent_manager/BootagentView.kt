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
import com.sandpolis.client.lifegem.api.AgentView
import tornadofx.*

class BootagentView : AgentView("Boot Agent") {

    override val root = vbox {
        titledpane("Boot Agent") {
        }
    }

    override fun setActive(profile: STDocument) {

    }

    override fun setInactive() {
        
    }
}
