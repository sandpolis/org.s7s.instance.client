//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.client.lifegem.ui.common.controller;

import java.util.Objects;

import com.google.common.eventbus.Subscribe;
import com.sandpolis.client.lifegem.stage.SandpolisStage;

/**
 * A controller that contains convenience fields.
 *
 * @author cilki
 * @since 5.0.0
 */
public abstract class FxController extends AbstractController {

	protected SandpolisStage stage;

	@Subscribe
	public void setStage(SandpolisStage stage) {
		if (this.stage != null)
			throw new IllegalStateException();

		this.stage = Objects.requireNonNull(stage);
	}

}
