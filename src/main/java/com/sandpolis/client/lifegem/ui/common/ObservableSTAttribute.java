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

import com.google.common.eventbus.Subscribe;
import com.sandpolis.core.instance.state.st.STAttribute;
import com.sandpolis.core.instance.state.st.STAttribute.ChangeEvent;

import javafx.beans.property.SimpleObjectProperty;

public class ObservableSTAttribute<T> extends SimpleObjectProperty<T> {

	public ObservableSTAttribute(STAttribute attribute) {
		set((T) attribute.get());
	}

	@Subscribe
	public void onAttributeChange(ChangeEvent event) {
		set((T) event.newValue().value());
	}

}
