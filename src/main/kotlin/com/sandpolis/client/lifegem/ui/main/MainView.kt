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

import com.sandpolis.client.lifegem.ui.common.FxUtil
import com.sandpolis.client.lifegem.ui.common.pane.CarouselPane
import com.sandpolis.core.instance.state.ConnectionOid
import com.sandpolis.core.instance.state.InstanceOid
import com.sandpolis.core.instance.state.ProfileOid
import com.sandpolis.core.instance.state.AgentOid
import com.sandpolis.core.instance.state.STStore
import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.core.net.connection.ConnectionStore
import com.sandpolis.core.net.network.NetworkStore
import com.sandpolis.core.net.state.STCmd
import com.sandpolis.core.net.state.st.entangled.EntangledDocument
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class MainView : View("Main") {

    val profiles = FxUtil.newObservable(STStore.STStore.get(InstanceOid.InstanceOid().profile))

    val hostList = tableview(profiles) {
        column<STDocument, String>("UUID") {
            FxUtil.newProperty(it.value.attribute(ProfileOid.UUID))
        }
        column<STDocument, String>("IP Address") {
            FxUtil.newProperty(it.value.attribute(ProfileOid.IP_ADDRESS))
        }
        column<STDocument, String>("Hostname") {
            FxUtil.newProperty(it.value.attribute(AgentOid.HOSTNAME))
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

    override fun onDock() {
        val preferredServer = NetworkStore.NetworkStore.preferredServer

        preferredServer.flatMap {
            ConnectionStore.ConnectionStore.getByCvid(it)
        }.ifPresent {
            STCmd.async().target(it).sync(InstanceOid.InstanceOid().profile(it.get(ConnectionOid.REMOTE_UUID)))
        }
    }

    override fun onUndock() {
    }
}