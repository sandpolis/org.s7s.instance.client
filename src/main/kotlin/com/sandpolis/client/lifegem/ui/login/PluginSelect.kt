//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.ui.login

import com.sandpolis.client.lifegem.ui.common.FxUtil
import com.sandpolis.core.instance.state.InstanceOids.ProfileOid.PluginOid
import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.scene.control.CheckBox
import javafx.scene.control.TableView
import tornadofx.*

class PluginSelect(val parentView: LoginView) : Fragment() {

    override val root = borderpane {
        top = label("This installation is missing the following plugins installed on the server")
        center = tableview(parentView.model.plugins) {
            columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY

            column<STDocument, String>("Name") {
                FxUtil.newProperty(it.value.attribute(PluginOid.NAME))
            }
            column<STDocument, String>("Identifier") {
                FxUtil.newProperty(it.value.attribute(PluginOid.PACKAGE_ID))
            }
            column<STDocument, String>("Description") {
                FxUtil.newProperty(it.value.attribute(PluginOid.DESCRIPTION))
            }
            column<STDocument, CheckBox>("Install?") {
                ReadOnlyObjectWrapper(CheckBox())
            }
        }
        bottom = buttonbar {
            button("Continue") {
                action {
                    parentView.model.loginPhase.set(LoginView.LoginPhase.COMPLETE)
                }
            }
        }
    }
}