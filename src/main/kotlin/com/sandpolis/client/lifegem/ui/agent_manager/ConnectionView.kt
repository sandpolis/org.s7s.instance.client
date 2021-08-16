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
import com.sandpolis.core.instance.state.InstanceOids
import com.sandpolis.core.net.state.STCmd
import com.sandpolis.core.net.state.st.entangled.EntangledDocument
import tornadofx.*
import java.util.concurrent.CompletionStage

class ConnectionView : AgentView("Inventory") {

    var entangled: CompletionStage<EntangledDocument>? = null

    override val root = vbox {
        titledpane("Network Connections") {
        }
    }

    override fun setActive(profile: STDocument) {
    //    entangled = STCmd.async().sync(
    //        InstanceOids.InstanceOids().profile(profile.attribute(InstanceOids.ProfileOid.UUID).asString()).bootagent.gptpartition)
    }

    override fun setInactive() {
    //    entangled?.thenApply { it.close() }
    }
}