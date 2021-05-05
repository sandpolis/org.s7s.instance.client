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

import com.sandpolis.client.lifegem.ui.about.AboutView
import com.sandpolis.client.lifegem.ui.common.SvgUtil
import com.sandpolis.client.lifegem.ui.Events.MainMenuOpenEvent
import com.sandpolis.client.lifegem.ui.Events.MainViewChangeEvent
import com.sandpolis.client.lifegem.ui.common.tray.Tray;
import javafx.geometry.Orientation
import tornadofx.*

class SideMenuView : Fragment() {

    val interfaceMenu = titledpane("Interface", collapsible = false) {

        graphic = SvgUtil.getSvg("/image/common/terminal-window-line.svg", 24.0, 24.0, textFillProperty())

        vbox {
            button ("List View") {
                action {
                    fire(MainViewChangeEvent("list"))
                }
            }

            button ("Graph View") {
                action {
                    fire(MainViewChangeEvent("graph"))
                }
            }
        }

        vbox {
            button("Hide interface") {
                setDisable(!Tray.isSupported()) // TODO this seems to be slow
                tooltip("The application will continue running in the background and the UI will be hidden.")
            }
        }
    }

    val aboutMenu = titledpane("About", collapsible = false) {
        vbox {
            button("About") {
                action {
                    find<AboutView>().openWindow()
                }

                tooltip("Show the about window")
            }
            button("Open Documentation")
        }
    }

    override val root = toolbar {
        setOrientation(Orientation.VERTICAL)

        button {
            graphic = SvgUtil.getSvg("/image/common/terminal-window-line.svg", 16.0, 16.0, textFillProperty())

            action {
                fire(MainMenuOpenEvent(interfaceMenu))
            }
        }

        button {
            graphic = SvgUtil.getSvg("/image/common/settings-5-line.svg", 16.0, 16.0, textFillProperty())
        }

        button {
            graphic = SvgUtil.getSvg("/image/common/settings-5-line.svg", 16.0, 16.0, textFillProperty())
        }

        button {
            graphic = SvgUtil.getSvg("/image/common/question-line.svg", 16.0, 16.0, textFillProperty())

            action {
                fire(MainMenuOpenEvent(aboutMenu))
            }
        }
    }
}
