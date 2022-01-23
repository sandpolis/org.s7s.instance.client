//============================================================================//
//                                                                            //
//            Copyright © 2015 - 2022 Sandpolis Software Foundation           //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPLv2. //
//                                                                            //
//============================================================================//

rootProject.name = "org.s7s.instance.client"

include("android")
include("common")
include("desktop")
include("ios")
include("web")

buildscript {
	repositories {
		maven {
			url = uri("https://plugins.gradle.org/m2/")
		}
	}
	dependencies {
		classpath("org.s7s:org.s7s.build:+")
	}
}
