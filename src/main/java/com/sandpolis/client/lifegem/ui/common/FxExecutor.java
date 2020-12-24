//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.client.lifegem.ui.common;

import java.util.concurrent.Executor;

import javafx.application.Platform;

public final class FxExecutor implements Executor {

	public static final FxExecutor INSTANCE = new FxExecutor();

	private FxExecutor() {
	}

	@Override
	public void execute(Runnable command) {
		Platform.runLater(command);
	}
}
