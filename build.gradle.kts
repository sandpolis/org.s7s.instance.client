//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.internal.os.OperatingSystem

plugins {
	id("java-library")
	kotlin("jvm") version "1.6.0"
	id("application")
	id("com.sandpolis.build.module")
	id("com.sandpolis.build.instance")
	id("com.sandpolis.build.publish")
}

application {
	mainModule.set("com.sandpolis.client.lifegem")
	mainClass.set("com.sandpolis.client.lifegem.Main")
}

tasks.named<JavaExec>("run") {
	environment.put("S7S_DEVELOPMENT_MODE", "true")
	environment.put("S7S_LOG_LEVELS", "io.netty=WARN,java.util.prefs=OFF,com.sandpolis=TRACE")
	jvmArgs = listOf("--add-opens", "javafx.graphics/javafx.scene=tornadofx")
}

tasks.withType<Jar>() {
	manifest {
		attributes["Application-Name"] = "Sandpolis"
	}
}

repositories {
	maven {
		url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions {
		jvmTarget = "17"
	}
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.+")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.+")
	testImplementation("org.testfx:testfx-core:4.0.16-alpha")
	testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
	testImplementation("org.testfx:openjfx-monocle:jdk-12.0.1+2")
	testImplementation("org.awaitility:awaitility:4.0.1")

	// https://github.com/nayuki/QR-Code-generator
	implementation("io.nayuki:qrcodegen:1.6.0")

	implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.0")

	if (project.getParent() == null) {
		implementation("com.sandpolis:core.client:+")
		implementation("com.sandpolis:core.instance:+")
	} else {
		implementation(project(":module:com.sandpolis.core.client"))
		implementation(project(":module:com.sandpolis.core.instance"))
	}

	if (OperatingSystem.current().isMacOsX()) {
		implementation("org.openjfx:javafx-base:17:mac")
		implementation("org.openjfx:javafx-graphics:17:mac")
		implementation("org.openjfx:javafx-controls:17:mac")
		implementation("org.openjfx:javafx-fxml:17:mac")
		implementation("org.openjfx:javafx-web:17:mac")
	} else if (OperatingSystem.current().isLinux()) {
		implementation("org.openjfx:javafx-base:17:linux")
		implementation("org.openjfx:javafx-graphics:17:linux")
		implementation("org.openjfx:javafx-controls:17:linux")
		implementation("org.openjfx:javafx-fxml:17:linux")
		implementation("org.openjfx:javafx-web:17:linux")
	} else if (OperatingSystem.current().isWindows()) {
		implementation("org.openjfx:javafx-base:17:windows")
		implementation("org.openjfx:javafx-graphics:17:windows")
		implementation("org.openjfx:javafx-controls:17:windows")
		implementation("org.openjfx:javafx-fxml:17:windows")
		implementation("org.openjfx:javafx-web:17:windows")
	}
}
