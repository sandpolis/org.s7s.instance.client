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

import com.sandpolis.core.instance.state.st.STDocument
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class GenerateArtifactFragment : Fragment() {

    val group: STDocument by param()

    private val model = object : ViewModel() {
        val artifactType = bind { SimpleStringProperty() }
        val artifactFormat = bind { SimpleStringProperty() }
    }

    // The valid vanilla formats
    val vanillaFormats = listOf(".jar", ".go", ".sh", ".exe", ".py").asObservable()

    // The valid micro formats
    val microFormats = listOf(".elf", ".exe").asObservable()

    override val root = form {
        fieldset {
            field("Agent executable") {
                combobox<String>(model.artifactType) {
                    items = listOf("Vanilla", "Micro").asObservable()
                }
                combobox<String>(model.artifactFormat, vanillaFormats) {
                    model.artifactType.addListener { p, o, n ->
                        items = vanillaFormats
                    }
                }
            }
        }
        buttonbar {
            button("Generate")
        }
    }
}
