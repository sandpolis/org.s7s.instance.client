//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
open module com.sandpolis.client.lifegem {
	exports com.sandpolis.client.lifegem.ui.common.button;
	exports com.sandpolis.client.lifegem.ui.common.tray;
	exports com.sandpolis.client.lifegem.ui.common.label;
	exports com.sandpolis.client.lifegem.stage;
	exports com.sandpolis.client.lifegem.init;
	exports com.sandpolis.client.lifegem.plugin;
	exports com.sandpolis.client.lifegem.ui.common.pane;
	exports com.sandpolis.client.lifegem.ui.common;
	exports com.sandpolis.client.lifegem.ui.common.field;
	exports com.sandpolis.client.lifegem.ui.common.controller;
	exports com.sandpolis.client.lifegem;

	requires kotlin.stdlib;
	requires com.google.common;
	requires com.google.protobuf;
	requires com.sandpolis.core.foundation;
	requires com.sandpolis.core.instance;
	requires com.sandpolis.core.client;
	requires com.sandpolis.core.clientserver;
	requires io.netty.common;
	requires io.netty.transport;
	requires java.desktop;
	requires java.xml;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.slf4j;
	requires tornadofx;
}
