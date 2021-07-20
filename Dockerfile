FROM adoptopenjdk:16-hotspot

# Set application directory
WORKDIR /app

# Set application entry
ENTRYPOINT ["java", "-cp", "/app/lib/*", "com.sandpolis.client.lifegem.Main"]

# Install dependencies
RUN apt-get update && apt-get install -y libgtk-3-0 libglu1-mesa mesa-utils && apt-get clean && rm -rf /var/lib/apt/lists/*

# Set environment
ENV S7S_RUNTIME_RESIDENCY     "container"
ENV S7S_PATH_LIB              "/app/lib"
ENV S7S_PATH_PLUGIN           "/app/plugin"
ENV S7S_PLUGINS_ENABLED       "true"

# Install application
COPY build/lib /app/lib
