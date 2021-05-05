//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.ui

import javafx.scene.control.TitledPane
import tornadofx.*

object Events {

    class MainViewChangeEvent(val view: String) : FXEvent()

    class MainMenuOpenEvent(val view: TitledPane) : FXEvent()
}
