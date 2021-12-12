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
import com.sandpolis.core.instance.state.InstanceOids.InstanceOids
import com.sandpolis.core.instance.state.InstanceOids.GroupOid.OperationOid
import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ProgressIndicator
import javafx.scene.layout.Region
import tornadofx.*

class GroupOperationLog(val extend: ObjectProperty<Region>, val group: STDocument) : Fragment() {

    override val root = titledpane("Operation Log", collapsible = false) {
        /*content = tableview(FxUtil.newObservable(InstanceOids().group(group.oid().last()).operation)) {
            column<STDocument, String>("Start Time") {
                FxUtil.newProperty(it.value.attribute(OperationOid.START_TIME))
            }
            column<STDocument, ProgressIndicator>("Progress") {
                SimpleObjectProperty(progressindicator {
                    progressProperty().bind(FxUtil.newProperty(it.value.attribute(OperationOid.PROGRESS)))
                })
            }
        }*/
    }
}