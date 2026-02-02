# Use OpenJDK base image with JDK 21
FROM eclipse-temurin:21-jdk
# Set working directory
WORKDIR /app
# Copy the jar file from target folder
COPY target/Authentication-0.0.1-SNAPSHOT.jar app.jar
# Expose the port your app runs on
EXPOSE 8081
# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
