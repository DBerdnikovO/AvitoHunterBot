FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/AvitoHunterBot-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} application.jar

EXPOSE 8081

CMD apt-get update -y

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]