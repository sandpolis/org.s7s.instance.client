FROM adoptopenjdk:15-hotspot AS base

# Set application directory
WORKDIR /app

# Install dependencies
RUN apt-get update && apt-get install -y libgtk-3-0 libglu1-mesa mesa-utils && apt-get clean && rm -rf /var/lib/apt/lists/*

# Sandpolis configuration
ENV SANDPOLIS_NET_CONNECTION_TLS    "true"
ENV SANDPOLIS_NET_LOGGING_DECODED   "false"
ENV SANDPOLIS_NET_LOGGING_RAW       "false"
ENV SANDPOLIS_PATH_LIB              "/app/lib"
ENV SANDPOLIS_PATH_PLUGIN           "/app/plugin"
ENV SANDPOLIS_PLUGINS_ENABLED       "true"

FROM gradle:jdk15 AS build
COPY --chown=gradle:gradle . .
RUN gradle --no-daemon :com.sandpolis.client.lifegem:imageSyncBuildContext

FROM base AS debug
COPY --from=build com.sandpolis.client.lifegem/build/docker/lib /app/lib

# Enable JVM debugging
#ENV JAVA_TOOL_OPTIONS "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:7000,server=y,suspend=y"

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/urandom", "-cp", "/app/lib/*", "com.sandpolis.client.lifegem.Main"]

FROM base AS production
COPY --from=build com.sandpolis.client.lifegem/build/docker/lib /app/lib

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/urandom", "-cp", "/app/lib/*", "com.sandpolis.client.lifegem.Main"]
