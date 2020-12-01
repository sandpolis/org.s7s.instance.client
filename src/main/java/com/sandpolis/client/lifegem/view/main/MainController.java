//============================================================================//
//                                                                            //
//                Copyright © 2015 - 2020 Subterranean Security               //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation at:                                //
//                                                                            //
//    https://mozilla.org/MPL/2.0                                             //
//                                                                            //
//=========================================================S A N D P O L I S==//
package com.sandpolis.client.lifegem.view.main;

import java.util.Objects;

import com.google.common.eventbus.Subscribe;
import com.sandpolis.client.lifegem.ui.common.controller.AbstractController;
import com.sandpolis.client.lifegem.ui.common.pane.CarouselPane;
import com.sandpolis.client.lifegem.ui.common.pane.ExtendPane;
import com.sandpolis.client.lifegem.view.main.Events.HostDetailCloseEvent;
import com.sandpolis.client.lifegem.view.main.Events.MenuCloseEvent;
import com.sandpolis.client.lifegem.view.main.Events.MenuOpenEvent;
import com.sandpolis.client.lifegem.view.main.Events.ViewChangeEvent;
import com.sandpolis.client.lifegem.view.main.list.HostListController;
import com.sandpolis.client.lifegem.view.main.menu.VerticalMenuController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Side;

public class MainController extends AbstractController {

	// TODO to config
	private static final Side MENU_SIDE = Side.LEFT;
	private static final Side DETAIL_SIDE = Side.RIGHT;

	@FXML
	private CarouselPane carousel;
	@FXML
	private ExtendPane extend;
	@FXML
	private VerticalMenuController menuController;
	@FXML
	private HostListController hostListController;

//	private Cache<FxProfile, Pane> detailCache;

	private StringProperty phase = new SimpleStringProperty();

	@FXML
	public void initialize() {
		register(menuController, hostListController);

//		detailCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build();

		phase.addListener((p, o, n) -> {
			if (o == n)
				return;

			carousel.moveTo(n);
		});
	}

	@Subscribe
	public void switchView(ViewChangeEvent event) {
		phase.set(Objects.requireNonNull(event.get()));
	}

}
