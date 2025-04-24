# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set a working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/Jenkins-gke-project-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
