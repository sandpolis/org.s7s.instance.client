//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.client.lifegem.view.main.menu;

import static com.sandpolis.core.instance.pref.PrefStore.PrefStore;

import com.sandpolis.client.lifegem.ui.common.controller.AbstractController;

import static com.sandpolis.client.lifegem.stage.StageStore.StageStore;

import javafx.fxml.FXML;

public class ManagementController extends AbstractController {

	@FXML
	public void open_network() {

		StageStore.create(stage -> {
			stage.setRoot("/fxml/view/network/Network.fxml");
			stage.setWidth(PrefStore.getInt("ui.view.about.width"));
			stage.setHeight(PrefStore.getInt("ui.view.about.height"));
			stage.setTitle("Network");
		});
	}

	@FXML
	public void open_add_client() {

		StageStore.create(stage -> {
			stage.setRoot("/fxml/view/add_client/AddClient.fxml");
			stage.setWidth(PrefStore.getInt("ui.view.about.width"));
			stage.setHeight(PrefStore.getInt("ui.view.about.height"));
			stage.setTitle("Add Client");
		});
	}

}
