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

import static com.sandpolis.client.lifegem.stage.StageStore.StageStore;

import com.sandpolis.client.lifegem.ui.common.FxUtil;
import com.sandpolis.core.instance.InitTask;
import com.sandpolis.core.instance.TaskOutcome;

import javafx.application.Application;
import javafx.stage.Stage;
import tornadofx.FX;

public class LifegemLoadUserInterface extends InitTask {

	/**
	 * The {@link Application} class for starting the user interface.
	 */
	public static class UI extends Application {

		private static Application singleton;

		public UI() {
			if (singleton != null)
				throw new IllegalStateException();

			singleton = this;
		}

		/**
		 * Get the application handle.
		 *
		 * @return The {@link Application} or {@code null} if it has not started
		 */
		public static Application getApplication() {
			return singleton;
		}

		@Override
		public void start(Stage main) throws Exception {

			// Ignore the primary stage and create a new one
			var stage = StageStore.create(s -> {
				s.setRoot("com.sandpolis.client.lifegem.ui.login.LoginView");
				// s.setWidth(PrefStore.getInt("ui.view.login.width"));
				// s.setHeight(PrefStore.getInt("ui.view.login.height"));
				// s.setResizable(false);
				s.setTitle(FxUtil.translate("stage.login.title"));
			});

			// Register application
			FX.registerApplication(this, stage);

		}
	}

	@Override
	public TaskOutcome run(TaskOutcome outcome) throws Exception {
		new Thread(() -> Application.launch(UI.class)).start();

		return outcome.success();
	}

	@Override
	public String description() {
		return "Load user interface";
	}

}
