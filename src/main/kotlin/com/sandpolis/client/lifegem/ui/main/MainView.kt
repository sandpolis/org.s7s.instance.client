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
import com.sandpolis.core.foundation.Platform
import com.sandpolis.core.instance.Metatypes
import com.sandpolis.core.instance.state.ConnectionOid
import com.sandpolis.core.instance.state.InstanceOid
import com.sandpolis.core.instance.state.ProfileOid
import com.sandpolis.core.instance.state.AgentOid
import com.sandpolis.core.instance.state.STStore
import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.core.net.connection.ConnectionStore
import com.sandpolis.core.net.network.NetworkStore
import com.sandpolis.core.net.state.STCmd
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Main") {

    val profiles = FxUtil.newObservable(InstanceOid.InstanceOid().profile) {
        //val attr = it.attribute(ProfileOid.INSTANCE_TYPE)
        //attr.isPresent() && attr.asInstanceType() == Metatypes.InstanceType.AGENT;
        true
    }

    val hostList = tableview(profiles) {
        column<STDocument, String>("Hostname") {
            FxUtil.newProperty(it.value.attribute(AgentOid.HOSTNAME))
        }
        column<STDocument, String>("IP Address") {
            FxUtil.newProperty(it.value.attribute(ProfileOid.IP_ADDRESS))
        }
        column<STDocument, Pane>("OS Type") {
            FxUtil.newProperty(it.value.attribute(AgentOid.OS_TYPE)) { value ->
                when (value) {
                    Platform.OsType.LINUX -> hbox {
                        imageview("image/platform/linux.png")
                        label("Linux")
                    }
                    else -> hbox {
                    }
                }
            }
        }
        column<STDocument, String>("Uptime") {
            FxUtil.newProperty(it.value.attribute(AgentOid.START_TIME))
        }
        column<STDocument, String>("Last Contact") {
            FxUtil.newProperty(it.value.attribute(AgentOid.CONTACT_TIME))
        }
        column<STDocument, String>("Latency") {
            FxUtil.newProperty(it.value.attribute(ProfileOid.LATENCY))
        }

        val expander = rowExpander {
            paddingLeft = 6
            tabpane {
                tab("Metadata") {
                    vbox {
                        hbox {
                            button("Reboot")
                        }
                        form {
                            fieldset("Test") {
                                field("UUID") {
                                    label(it.attribute(ProfileOid.UUID).asString())
                                }
                            }
                        }
                    }
                }
            }
        }
        expander.isVisible = false
        selectionModel.selectedItemProperty().onChange {
            expander.getExpandedProperty(it).set(true)
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
            STCmd.async().target(it).sync(InstanceOid.InstanceOid().profile)
        }
    }

    override fun onUndock() {
    }
}
