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

import java.awt.AWTException;

import com.sandpolis.client.lifegem.ui.common.button.SvgButton;
import com.sandpolis.client.lifegem.ui.common.controller.AbstractController;
import com.sandpolis.client.lifegem.ui.common.tray.Tray;
import com.sandpolis.client.lifegem.view.main.Events.AuxDetailOpenEvent;
import com.sandpolis.client.lifegem.view.main.Events.ViewChangeEvent;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class InterfaceController extends AbstractController {

	@FXML
	private SvgButton btn_background;

	@FXML
	private void initialize() {
		btn_background.setDisable(!Tray.isSupported());
	}

	@FXML
	private void open_list() {
		post(new ViewChangeEvent("list"));
	}

	@FXML
	private void open_graph() {
		post(new ViewChangeEvent("graph"));
	}

	@FXML
	private void open_console() {
		post(new AuxDetailOpenEvent("console"));
	}

	@FXML
	private void open_status() {
		post(new AuxDetailOpenEvent("status"));
	}

	@FXML
	private void background() throws AWTException {
		Tray.background();
	}

	@FXML
	private void quit() {
		Platform.exit();
		System.exit(0);
	}

}
