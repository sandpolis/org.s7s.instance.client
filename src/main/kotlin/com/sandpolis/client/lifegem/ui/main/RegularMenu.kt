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
import com.sandpolis.client.lifegem.ui.server_manager.ServerManagerView
import tornadofx.*

class RegularMenuView : Fragment() {

    override val root = menubar {
        menu("Interface") {
            item("List View", "Shortcut+L")
            item("Graph View", "Shortcut+G")
            separator()
            item("Move to system tray") {
                tooltip("The application will continue running in the background and the UI will be hidden.")
            }
        }
        menu("Management") {
            item("Server Manager") {
                action {
                    find<ServerManagerView>().openWindow()
                }
            }
        }
        menu("Help") {
            item("About") {
                action {
                    find<AboutView>().openWindow()
                }
                tooltip("Show the about window")
            }
        }
    }
}
