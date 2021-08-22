package com.sandpolis.client.lifegem.plugin;

import com.sandpolis.core.instance.state.st.STDocument;

import tornadofx.View;

public abstract class AgentViewExtension extends View {

	private String name;

	public AgentViewExtension(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract void nowVisible(STDocument profile);

	public abstract void nowInvisible();
}
