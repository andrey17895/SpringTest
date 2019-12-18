FROM openjdk:8-jdk-alpine
EXPOSE 8080
RUN apk --no-cache add curl
ADD target/SpringTest-0.0.1-SNAPSHOT.jar springtest.jar
HEALTHCHECK CMD curl --silent --fail localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "springtest.jar"]