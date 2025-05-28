# Base image with OpenJDK 17 slim
FROM openjdk:17-jdk-slim

# Install bash and bash-completion
RUN apt-get update && apt-get install -y bash bash-completion && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy jar file - assume jar banane ke baad target folder me hoga
COPY target/*.jar app.jar

# Copy application.properties agar aapke resources me hai to
COPY src/main/resources/application.properties ./application.properties

# Run the application with spring config location set
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.properties,file:/app/application.properties"]

