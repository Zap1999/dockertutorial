# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="avashchenock@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

RUN apk update && apk add bash

# Make port 8082 available to the world outside this container
EXPOSE 8082

# The application's jar file
ARG JAR_FILE=./target/messagereceiver-0.0.1-SNAPSHOT.jar

ARG KEYSTORE=./src/main/resources/keystore/receiver.p12
ARG CERT=./src/main/resources/keystore/publicReceiverCert.pem

# Add the application's jar to the container
ADD ${JAR_FILE} messagereceiver.jar

ADD ${KEYSTORE} receiver.p12
ADD ${CERT} publicReceiverCert.pem

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/messagereceiver.jar"]