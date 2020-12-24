//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.client.lifegem;

import com.sandpolis.core.instance.MainDispatch;
import com.sandpolis.core.instance.Metatypes.InstanceFlavor;
import com.sandpolis.core.instance.Metatypes.InstanceType;

/**
 * {@code com.sandpolis.client.lifegem} entry point.
 *
 * @since 5.0.0
 */
public final class Main {
	private Main() {
	}

	public static void main(String[] args) {
		MainDispatch.dispatch(Client.class, args, InstanceType.CLIENT, InstanceFlavor.LIFEGEM);
	}

}
