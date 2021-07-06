//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.ui.server_manager

import com.sandpolis.client.lifegem.ui.common.FxUtil
import com.sandpolis.core.instance.state.*
import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.core.net.connection.ConnectionStore
import com.sandpolis.core.net.network.NetworkStore
import com.sandpolis.core.net.state.STCmd
import javafx.beans.property.ReadOnlyObjectWrapper
import tornadofx.*

class ServerManagerView : View("Server Manager") {

    val controller: ServerManagerController by inject()

    val servers = FxUtil.newObservable(InstanceOid.InstanceOid().profile.server)
    val users = FxUtil.newObservable(InstanceOid.InstanceOid().user)
    val listeners = FxUtil.newObservable(InstanceOid.InstanceOid().profile.server.listener)
    val groups = FxUtil.newObservable(InstanceOid.InstanceOid().group)

    override  val root = drawer {
        prefWidth = 800.0
        prefHeight = 400.0

        item("Servers") {
            tableview(listeners) {
                column<STDocument, String>("UUID") {
                    FxUtil.newProperty(it.value.attribute(ProfileOid.UUID))
                }
            }
        }
        item("Listeners") {
            borderpane {
                bottom =
                    buttonbar {
                        button("Add") {

                        }
                    }
                center =
                    tableview(listeners) {
                        column<STDocument, String>("Bind Address") {
                            FxUtil.newProperty(it.value.attribute(ListenerOid.ADDRESS))
                        }
                        column<STDocument, String>("Listen Port") {
                            FxUtil.newProperty(it.value.attribute(ListenerOid.PORT))
                        }
                    }
            }
        }
        item("Users") {
            borderpane {
                bottom =
                    buttonbar {
                        button("Add") {

                        }
                    }
                center =
                    tableview(users) {
                        column<STDocument, String>("Username") {
                            FxUtil.newProperty(it.value.attribute(UserOid.USERNAME))
                        }
                        column<STDocument, String>("Password age") {
                            ReadOnlyObjectWrapper("")
                        }
                        column<STDocument, String>("Last login") {
                            ReadOnlyObjectWrapper("")
                        }
                        column<STDocument, String>("Login address") {
                            ReadOnlyObjectWrapper("")
                        }
                    }
            }
        }
        item("Agent Groups") {
            borderpane {
                bottom =
                    buttonbar {
                        button("Add") {
                            action {
                                find<GroupCreatorView>().openWindow()
                            }
                        }

                        button("Import") {}
                    }
                center =
                    tableview(groups) {
                        column<STDocument, String>("Name") {
                            FxUtil.newProperty(it.value.attribute(GroupOid.NAME))
                        }
                        //readonlyColumn("", FxGroup::name) {
                        //    cellFormat { graphic = button("1") }
                        //}
                    }
            }
        }
    }

    override fun onDock() {
        val preferredServer = NetworkStore.NetworkStore.preferredServer

        preferredServer.flatMap {
            ConnectionStore.ConnectionStore.getByCvid(it)
        }.ifPresent {
            STCmd.async().target(it).sync(InstanceOid.InstanceOid().user)
        }
    }

    override fun onUndock() {
    }
}
