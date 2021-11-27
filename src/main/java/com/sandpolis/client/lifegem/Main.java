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

import com.sandpolis.client.lifegem.init.LifegemLoadStores;
import com.sandpolis.client.lifegem.init.LifegemLoadUserInterface;
import com.sandpolis.core.instance.Entrypoint;
import com.sandpolis.core.instance.Metatypes.InstanceFlavor;
import com.sandpolis.core.instance.Metatypes.InstanceType;
import com.sandpolis.core.instance.init.InstanceLoadPlugins;

public final class Main extends Entrypoint {
	private Main(String[] args) {
		super(Main.class, InstanceType.CLIENT, InstanceFlavor.CLIENT_LIFEGEM);

		register(new LifegemLoadStores());
		register(new InstanceLoadPlugins());
		register(new LifegemLoadUserInterface());

		start("Sandpolis Desktop Client", args);
	}

	public static void main(String[] args) {
		new Main(args);
	}

}
