//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.client.lifegem.view.main;

import java.util.List;

import com.sandpolis.core.instance.state.oid.Oid;
import com.sandpolis.core.instance.state.st.STAttribute;

import javafx.scene.layout.Region;

public final class Events {

	/**
	 * Close the main menu.
	 */
	public static record MenuCloseEvent() {
	}

	/**
	 * Open the main menu with the given {@link Region}.
	 */
	public static record MenuOpenEvent(Region region) {
	}

	/**
	 * Close the host detail.
	 */
	public static record HostDetailCloseEvent() {
	}

	/**
	 * Open the host detail.
	 */
//	public static class HostDetailOpenEvent extends ParameterizedEvent<FxProfile> {
//		public HostDetailOpenEvent(FxProfile parameter) {
//			super(parameter);
//		}
//	}

	/**
	 * Change the headers in the host list.
	 */
	public static record HostListHeaderChangeEvent(List<Oid> oids) {
	}

	/**
	 * Open the auxiliary detail.
	 */
	public static record AuxDetailOpenEvent(String detail) {
	}

	/**
	 * Close the auxiliary detail.
	 */
	public static record AuxDetailCloseEvent(String detail) {
	}

	/**
	 * Change the primary view.
	 */
	public static record ViewChangeEvent(String view) {
	}

}
