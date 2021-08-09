//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

package com.sandpolis.client.lifegem.api

import com.sandpolis.core.instance.state.st.STDocument
import tornadofx.*

abstract class AgentView(val name: String) : View() {

	abstract fun setActive(profile: STDocument)

	abstract fun setInactive()
}
