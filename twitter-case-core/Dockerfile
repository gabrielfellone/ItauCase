FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9022

# The application's jar file
ARG JAR_FILE=target/case-itau-twitter-0.0.1-SNAPSHOT

# Add the application's jar to the container
ADD ${JAR_FILE} case-itau-twitter-0.0.1-SNAPSHOT

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/case-itau-twitter-0.0.1-SNAPSHOT"]
WORKDIR /