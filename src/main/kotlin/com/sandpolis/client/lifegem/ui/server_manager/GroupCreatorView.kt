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

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class GroupCreatorView : View() {

    private val model = object : ViewModel() {
        val groupName = bind { SimpleStringProperty() }

        // Whether a connection test is currently pending
        val connectionTestPending = bind { SimpleBooleanProperty() }

        val connectionInterval = bind { SimpleIntegerProperty() }

        val pollingMode = bind { SimpleBooleanProperty() }
        val strictCerts = bind { SimpleBooleanProperty() }
    }

    override val root = borderpane {
        center = scrollpane {
            isFitToWidth = true
            squeezebox {
                fold("Metadata", expanded = true) {
                    isCollapsible = false
                    form {
                        fieldset {
                            field("Group Name") {
                                textfield(model.groupName) {
                                    tooltip("The descriptive name of the group")
                                    filterInput { change ->
                                        !change.isAdded ||
                                                change.controlNewText.let {
                                                    it.matches("^[A-Za-z0-9 ]*$".toRegex())
                                                }
                                    }
                                }
                            }
                        }
                    }
                }
                fold("Network") {
                    form {
                        fieldset(labelPosition = Orientation.VERTICAL) {
                            field("Server Address") {
                                textfield {
                                    disableProperty().bind(model.connectionTestPending)
                                    filterInput { change ->
                                        !change.isAdded ||
                                                change.controlNewText.let {
                                                    it.matches("^[A-Za-z0-9\\.\\-]*$".toRegex())
                                                }
                                    }
                                }
                                button("Test Connection") {
                                    disableProperty().bind(model.connectionTestPending)
                                    tooltip("Attempt a test connection to the server")
                                }
                            }
                            field("Connection Interval") {
                                spinner(editable = true, model.connectionInterval) {
                                    tooltip("The connection interval in milliseconds")
                                }
                                label("ms")
                            }
                            field {
                                checkbox("Strict Certificates", model.strictCerts) {
                                    tooltip(
                                            "Whether the connection will fail if the server's certificate isn't trusted")
                                }
                                checkbox("Polling Mode", model.pollingMode) {
                                    tooltip(
                                            "Whether the connection will terminate if the server has nothing to send")
                                }
                            }
                        }
                    }
                }
                fold("Authentication") {
                    togglegroup {
                        radiobutton("Certificate") {
                            tooltip("A client certificate will be used to authenticate agents")
                        }
                        radiobutton("Password") {
                            tooltip("The given password will be used to authenticate agents")
                        }
                        radiobutton("No authentication") {
                            tooltip("Agents will not use any form of authentication")
                        }
                    }
                }
                fold("Features") {
                    form {
                        hbox {
                            fieldset("Platforms") {
                                checkbox("Linux")
                                checkbox("Windows")
                                checkbox("macOS")
                            }
                            fieldset("Architectures") {
                                checkbox("x86")
                                checkbox("x86_64")
                            }
                            fieldset("Plugins") {
                                checkbox("com.sandpolis.plugin.desktop")
                            }
                        }
                    }
                }
                fold("Runtime") {
                    checkbox("Remove installer on success")
                    checkbox("Recover from failures")
                    checkbox("Request highest privileges")
                    form {
                        fieldset {
                            field("Memory Reservation") { slider(100) }
                            field("CPU Limit") { slider(100) }
                        }
                    }
                }
            }
        }
        bottom = buttonbar { button("Create Group") }
    }
}