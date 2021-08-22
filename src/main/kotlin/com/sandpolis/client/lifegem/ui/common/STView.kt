package com.sandpolis.client.lifegem.ui.common

import com.sandpolis.core.instance.state.st.STDocument
import javafx.scene.control.TreeItem
import tornadofx.*

class STView(val document: STDocument) : Fragment() {

    override val root = treeview<STDocument> {
        root = TreeItem(document)

        cellFormat {
            text = it.oid().last()
        }

        populate { parent ->
            if (parent.value.documentCount() == 0) {
                null
            } else {
                FxUtil.newObservable(parent.value.oid())
            }
        }
    }
}