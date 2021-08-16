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
import com.sandpolis.client.lifegem.ui.common.pane.ExtendPane
import com.sandpolis.core.instance.state.InstanceOids.*
import com.sandpolis.core.instance.state.InstanceOids.ProfileOid.ServerOid.ListenerOid
import com.sandpolis.core.instance.state.st.STDocument
import com.sandpolis.core.net.state.STCmd
import javafx.beans.binding.Bindings
import javafx.beans.property.*
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import tornadofx.*

class ServerManagerView : View("Server Manager") {

    private val model = object : ViewModel() {
        val groupDialogProperty = bind { SimpleObjectProperty<Region>() }
        val selectedGroupProperty = bind { SimpleObjectProperty<STDocument>() }
    }

    val group = titledpane (collapsible = false) {
            graphic = hbox {
                label("test") {
                    hboxConstraints {
                        hGrow = Priority.ALWAYS
                    }
                }
                button("Generate") {
                    enableWhen(Bindings.isNotNull(model.selectedGroupProperty))
                    action {
                        model.groupDialogProperty.set(GenerateArtifactFragment(model.groupDialogProperty).root)
                    }
                }
                button("Add") {
                    action {
                        model.groupDialogProperty.set(GroupCreatorView(model.groupDialogProperty).root)
                    }
                }
                button("Import") {
                    action {

                    }
                }
                button("Export") {
                    enableWhen(Bindings.isNotNull(model.selectedGroupProperty))
                    action {

                    }
                }
            }
        content = tableview(FxUtil.newObservable(InstanceOids().group)) {
            column<STDocument, String>("Name") {
                FxUtil.newProperty(it.value.attribute(GroupOid.NAME))
            }

            model.selectedGroupProperty.bind(selectionModel.selectedItemProperty())
            selectionModel.selectedItemProperty().addListener { _, _, n ->
                model.groupDialogProperty.set(GroupOperationLog(model.groupDialogProperty, n).root)
            }
        }
    }

    override val root = drawer {
        prefWidth = 800.0
        prefHeight = 400.0

        item("Servers") {
            tableview(FxUtil.newObservable(InstanceOids().profile.server)) {
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
                    tableview(FxUtil.newObservable(InstanceOids().profile.server.listener)) {
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
                    tableview(FxUtil.newObservable(InstanceOids().user)) {
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
                center = ExtendPane(group).apply {
                    regionBottomProperty().bind(model.groupDialogProperty)
                }
            }
        }
    }

    override fun onDock() {
        STCmd.async().sync(InstanceOids().user)
        STCmd.async().sync(InstanceOids().group)
    }

    override fun onUndock() {
    }
}
