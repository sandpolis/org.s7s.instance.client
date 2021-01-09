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

import com.sandpolis.client.lifegem.ui.common.pane.ExtendPane
import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.layout.Region
import tornadofx.*

class AgentManagerView : Fragment() {

    val profile: STDocument by param()

    private val model =
            object : ViewModel() {
                val extendBottom = bind { SimpleObjectProperty<Region>() }
            }

    val menuList = vbox {

    }

    val content: Region = borderpane {
        center = label("center")
    }

    override val root =
        borderpane {
            left =
                titledpane("Hostname") {
                    setCollapsible(false)
                    setPrefWidth(100.0)
                    vbox {
                        label("hostname")
                        label("public ip")
                        label("os")
                    }
                    flowpane {
                        setHgap(10.0)
                        setVgap(10.0)
                        setAlignment(Pos.CENTER)

                        button("P") {
                            tooltip("Poweroff the host")
                        }
                        button("R") {
                            tooltip("Restart the host")
                        }
                        button("C") {
                            tooltip("Establish a persistent connection to the host")
                        }
                        button("T") {
                            tooltip("Terminate the persistent connection to the host")
                        }
                    }
                    vbox {

                    }
                }
            center = ExtendPane(content).apply {
                regionBottomProperty().bind(model.extendBottom)
            }
        }
}
