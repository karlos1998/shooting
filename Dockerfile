# Use the official OpenJDK 23 image as the base
FROM openjdk:23-oracle

# Argument for JAR file, default to build/libs/*.jar if not specified
ARG JAR_FILE=build/libs/*.jar

# Copy the application JAR to the container
COPY ${JAR_FILE} app.jar

# Specify the entry point command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
