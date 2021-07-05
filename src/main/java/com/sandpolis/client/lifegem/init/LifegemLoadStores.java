//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.client.lifegem.init;

import static com.sandpolis.core.instance.plugin.PluginStore.PluginStore;
import static com.sandpolis.core.instance.pref.PrefStore.PrefStore;
import static com.sandpolis.core.instance.profile.ProfileStore.ProfileStore;
import static com.sandpolis.core.instance.state.STStore.STStore;
import static com.sandpolis.core.instance.thread.ThreadStore.ThreadStore;
import static com.sandpolis.core.net.connection.ConnectionStore.ConnectionStore;
import static com.sandpolis.core.net.exelet.ExeletStore.ExeletStore;
import static com.sandpolis.core.net.network.NetworkStore.NetworkStore;
import static com.sandpolis.core.net.stream.StreamStore.StreamStore;

import java.util.List;
import java.util.concurrent.Executors;

import com.sandpolis.core.instance.Entrypoint;
import com.sandpolis.core.instance.InitTask;
import com.sandpolis.core.instance.TaskOutcome;
import com.sandpolis.core.instance.state.oid.Oid;
import com.sandpolis.core.instance.state.st.EphemeralDocument;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import javafx.application.Platform;

public class LifegemLoadStores extends InitTask {

	@Override
	public TaskOutcome run(TaskOutcome outcome) throws Exception {
		Platform.startup(() -> {
		});

		STStore.init(config -> {
			config.concurrency = 1;
			config.root = new EphemeralDocument(null, null);
		});

		ProfileStore.init(config -> {
			config.collection = Oid.of("/profile").get();
		});

		ThreadStore.init(config -> {
			config.defaults.put("net.exelet", new NioEventLoopGroup(2));
			config.defaults.put("net.connection.outgoing", new NioEventLoopGroup(2));
			config.defaults.put("net.message.incoming", new UnorderedThreadPoolEventExecutor(2));
			config.defaults.put("store.event_bus", Executors.newSingleThreadExecutor());
		});

		PrefStore.init(config -> {
			config.instance = Entrypoint.data().instance();
			config.flavor = Entrypoint.data().flavor();

			config.defaults.put("ui.help", true);
			config.defaults.put("ui.animations", true);
			config.defaults.put("ui.main.view", "list");
			config.defaults.put("ui.main.console", false);
			config.defaults.put("ui.tray.minimize", true);
			config.defaults.put("ui.theme", "Crimson");

			config.defaults.put("ui.view.login.width", 535);
			config.defaults.put("ui.view.login.height", 415);
			config.defaults.put("ui.view.main.width", 770);
			config.defaults.put("ui.view.main.height", 345);
			config.defaults.put("ui.view.about.width", 660);
			config.defaults.put("ui.view.about.height", 400);
			config.defaults.put("ui.view.generator.width", 700);
			config.defaults.put("ui.view.generator.height", 400);
		});

		ExeletStore.init(config -> {
			config.exelets = List.of();
		});

		StreamStore.init(config -> {
		});

		NetworkStore.init(config -> {
			config.collection = Oid.of("/network_connection").get();
		});

		ConnectionStore.init(config -> {
			config.collection = Oid.of("/connection").get();
		});

		PluginStore.init(config -> {
			config.collection = Oid.of("/profile//plugin", Entrypoint.data().uuid()).get();
		});

		return outcome.success();
	}

	@Override
	public String description() {
		return "Load stores";
	}

	@Override
	public boolean fatal() {
		return true;
	}
}
