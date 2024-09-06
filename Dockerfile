# Stage 1: Build the application
FROM openjdk:21 AS build
WORKDIR /app
# Copy the Maven wrapper and project files
COPY . .
# Ensure the Maven wrapper script is executable
RUN chmod +x mvnw
# Build the application
RUN ./mvnw install
# List the contents of /app for verification
RUN ls -la /app

# Stage 2: Package the application into Tomcat
FROM tomcat:latest
# Set the working directory to Tomcat's webapps folder
WORKDIR webapps

# Copy the JAR file from the build stage and rename it to ROOT.jar
COPY --from=build /app/target/CICDApp-0.0.1-SNAPSHOT.jar .
RUN rm -rf ROOT && mv CICDApp-0.0.1-SNAPSHOT.jar ROOT.jar

# Set the default command to start Tomcat
#ENTRYPOINT ["catalina.sh", "run"]
