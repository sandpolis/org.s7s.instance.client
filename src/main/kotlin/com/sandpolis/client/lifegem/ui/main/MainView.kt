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

import com.sandpolis.client.lifegem.ui.Events.MainMenuOpenEvent
import com.sandpolis.client.lifegem.ui.Events.MainViewChangeEvent
import com.sandpolis.client.lifegem.ui.agent_manager.AgentManagerView
import com.sandpolis.client.lifegem.ui.common.FxUtil
import com.sandpolis.client.lifegem.ui.common.pane.CarouselPane
import com.sandpolis.client.lifegem.ui.common.pane.ExtendPane
import com.sandpolis.core.foundation.Platform
import com.sandpolis.core.instance.state.InstanceOids.ProfileOid.AgentOid
import com.sandpolis.core.instance.state.InstanceOids.InstanceOids
import com.sandpolis.core.instance.state.InstanceOids.ProfileOid
import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.core.net.state.STCmd
import javafx.geometry.Side
import javafx.scene.control.TableView
import javafx.scene.layout.Pane
import tornadofx.*

class MainView : View("Main") {

    val profiles = FxUtil.newObservable(InstanceOids().profile) /*{
        val attr = it.attribute(ProfileOid.INSTANCE_TYPE)
        attr.isPresent() && attr.asInstanceType() == Metatypes.InstanceType.AGENT;
    }*/

    val hostGraph = pane {

    }

    val hostList = tableview(profiles) {

        columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY

        column<STDocument, String>("Hostname") {
            FxUtil.newProperty(it.value.attribute(AgentOid.HOSTNAME))
        }
        column<STDocument, String>("Instance Type") {
            FxUtil.newProperty(it.value.attribute(ProfileOid.INSTANCE_TYPE))
        }
        column<STDocument, Pane>("OS Type") {
            FxUtil.newProperty(it.value.attribute(AgentOid.OS_TYPE)) { value ->
                when (value) {
                    Platform.OsType.LINUX -> hbox {
                        imageview("image/platform/linux.png")
                        label("Linux")
                    }
                    Platform.OsType.WINDOWS -> hbox {
                        imageview("image/platform/windows_10.png")
                        label("Windows")
                    }
                    Platform.OsType.MACOS -> hbox {
                        imageview("image/platform/osx.png")
                        label("macOS")
                    }
                    else -> hbox {
                        label("Unknown")
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
        column<STDocument, String>("Status") {
            FxUtil.newProperty(it.value.attribute(ProfileOid.STATUS))
        }
        column<STDocument, String>("Agent Path") {
            FxUtil.newProperty(it.value.attribute(AgentOid.LOCATION))
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
                                field ("Upload traffic") {
                                    label(FxUtil.newProperty<String>(it.attribute(AgentOid.CONTACT_TIME)))
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

        contextmenu {
            item("Control Panel").action {
                find<AgentManagerView>(mapOf(AgentManagerView::profile to selectedItem)).openWindow()
            }
        }
    }

    val carousel = CarouselPane(hostList, hostGraph).apply {
        directionProperty().set(Side.TOP)
    }

    val extend = ExtendPane(carousel).apply {

    }

    override val root = borderpane {
        if ("" == "") {
            center = extend
            center.viewOrder = 2.0
            left<SideMenuView>()
        } else {
            center = carousel
            top<RegularMenuView>()
        }
    }

    override fun onDock() {
        STCmd.async().sync(InstanceOids().profile)
    }

    override fun onUndock() {
    }

    init {
        subscribe<MainViewChangeEvent> { event ->
            when (event.view) {
                "list" -> carousel.moveTo(0)
                "graph" -> carousel.moveTo(1)
                else -> {
                }
            }
        }

        subscribe<MainMenuOpenEvent> { event ->
            if (event.view != extend.regionLeftProperty().get()) {
                extend.regionLeftProperty().set(event.view)
            } else {
                extend.regionLeftProperty().set(null)
            }
        }
    }
}
