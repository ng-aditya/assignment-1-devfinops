## Stage 1: Build a native binary using the official Quarkus builder image (UBI 9 + GraalVM CE)
FROM quay.io/quarkus/ubi9-quarkus-graalvmce-builder-image:jdk-25 AS build

WORKDIR /project

# Keep Gradle caches inside the layerable build context
# ENV GRADLE_USER_HOME=/project/.gradle

# Copy Gradle wrapper + build scripts first (changes rarely)
COPY --chown=quarkus:quarkus --chmod=0755 gradlew .
COPY --chown=quarkus:quarkus gradle gradle
COPY --chown=quarkus:quarkus settings.gradle.kts .
COPY --chown=quarkus:quarkus build.gradle.kts .
COPY --chown=quarkus:quarkus gradle.properties .

# NOTE: We intentionally avoid a dedicated `dependencies` layer here.
# With Podman/buildah on constrained builder storage, caching the full Gradle
# distribution (especially `*-all.zip` with docs/javadoc) can exhaust space.

# Copy application sources last (changes frequently)
COPY --chown=quarkus:quarkus src ./src

# Build native executable (tests already run in CI via ./gradlew test on host; skip here for speed)
RUN ./gradlew --no-daemon build -Dquarkus.native.enabled=true -Dquarkus.package.jar.enabled=false \
    -x test --stacktrace

## Stage 2: Minimal runtime image (official Quarkus-recommended UBI 9 minimal base)
FROM registry.access.redhat.com/ubi9/ubi-minimal:9.7

WORKDIR /work/
RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work

COPY --from=build --chown=1001:root --chmod=0755 /project/build/*-runner /work/application

EXPOSE 8080
USER 1001

ENTRYPOINT ["./application", "-Dquarkus.http.host=0.0.0.0"]
