//============================================================================//
//                                                                            //
//            Copyright © 2015 - 2022 Sandpolis Software Foundation           //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPLv2. //
//                                                                            //
//============================================================================//

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.internal.os.OperatingSystem

plugins {
	id("java-library")
	kotlin("jvm") version "1.6.10"
	id("application")
	id("org.s7s.build.module")
	id("org.s7s.build.instance")
	id("org.s7s.build.publish")
}

application {
	mainModule.set("org.s7s.instance.client.desktop")
	mainClass.set("org.s7s.instance.client.desktop.Main")
}

tasks.named<JavaExec>("run") {
	environment.put("S7S_DEVELOPMENT_MODE", "true")
	environment.put("S7S_LOG_LEVELS", "io.netty=WARN,java.util.prefs=OFF,org.s7s=TRACE")
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

	api("no.tornado:tornadofx:2.0.0-SNAPSHOT")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.0")

	if (project.getParent() == null) {
		implementation("org.s7s:core.client:+")
		implementation("org.s7s:core.instance:+")
	} else {
		implementation(project(":core:org.s7s.core.client"))
		implementation(project(":core:org.s7s.core.instance"))
	}

	if (OperatingSystem.current().isMacOsX()) {
		api("org.openjfx:javafx-base:17:mac")
		api("org.openjfx:javafx-graphics:17:mac")
		api("org.openjfx:javafx-controls:17:mac")
		api("org.openjfx:javafx-fxml:17:mac")
		api("org.openjfx:javafx-web:17:mac")
	} else if (OperatingSystem.current().isLinux()) {
		api("org.openjfx:javafx-base:17:linux")
		api("org.openjfx:javafx-graphics:17:linux")
		api("org.openjfx:javafx-controls:17:linux")
		api("org.openjfx:javafx-fxml:17:linux")
		api("org.openjfx:javafx-web:17:linux")
	} else if (OperatingSystem.current().isWindows()) {
		api("org.openjfx:javafx-base:17:win")
		api("org.openjfx:javafx-graphics:17:win")
		api("org.openjfx:javafx-controls:17:win")
		api("org.openjfx:javafx-fxml:17:win")
		api("org.openjfx:javafx-web:17:win")
	}
}
