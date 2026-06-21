plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(enforcedPlatform("${property("quarkusPlatformGroupId")}:${property("quarkusPlatformArtifactId")}:${property("quarkusPlatformVersion")}"))

    implementation("io.quarkus:quarkus-rest-jackson")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-flyway")
    // Observability
        // logging
    implementation("io.quarkus:quarkus-logging-json")
        // tracing
    implementation("io.quarkus:quarkus-micrometer-opentelemetry")
        // metrics
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
        // health & info
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-info")
    implementation("io.quarkus:quarkus-virtual-threads")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-jdbc-h2")
    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<Test>().configureEach {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

