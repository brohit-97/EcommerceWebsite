# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project's build artifact from the host to the container
COPY target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]