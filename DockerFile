# Java 21 base image
FROM eclipse-temurin:21-jre

# App directory
WORKDIR /app

# Copy jar
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run app
ENTRYPOINT ["java","-jar","app.jar"]

