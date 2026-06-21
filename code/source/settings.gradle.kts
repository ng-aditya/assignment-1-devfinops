pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        val quarkusPluginId = providers.gradleProperty("quarkusPluginId").get()
        val quarkusPluginVersion = providers.gradleProperty("quarkusPluginVersion").get()
        id(quarkusPluginId) version quarkusPluginVersion
    }
}

rootProject.name = "assignment-1"
