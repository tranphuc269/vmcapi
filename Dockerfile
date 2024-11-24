# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build files
COPY pom.xml ./
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean install

# Copy the built JAR file to the working directory
COPY target/vmcapi-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]