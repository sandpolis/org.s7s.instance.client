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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.eventbus.Subscribe;
import com.sandpolis.core.instance.state.st.STDocument;
import com.sandpolis.core.instance.state.st.STDocument.DocumentAddedEvent;
import com.sandpolis.core.instance.state.st.STDocument.DocumentRemovedEvent;

import javafx.collections.ModifiableObservableListBase;

public class ObservableSTDocument extends ModifiableObservableListBase<STDocument> {

	private final STDocument document;
	private final Predicate<STDocument> filter;
	private final List<STDocument> backing;

	public ObservableSTDocument(STDocument document, Predicate<STDocument> filter) {
		this.document = document;
		this.filter = filter;
		this.backing = new ArrayList<>();

		document.addListener(this);
	}

	@Subscribe
	public void onDocumentAdded(DocumentAddedEvent event) {

		if (document.oid().equals(event.document().oid()) && filter.test(event.newDocument())) {
			add(event.newDocument());
		}
	}

	@Subscribe
	public void onDocumentRemoved(DocumentRemovedEvent event) {
		if (document == event.document()) {
			remove(event.oldDocument());
		}
	}

	@Override
	public STDocument get(int index) {
		return backing.get(index);
	}

	@Override
	public int size() {
		return backing.size();
	}

	@Override
	protected void doAdd(int index, STDocument element) {
		backing.add(index, element);
	}

	@Override
	protected STDocument doSet(int index, STDocument element) {
		return backing.set(index, element);
	}

	@Override
	protected STDocument doRemove(int index) {
		return backing.remove(index);
	}

}
